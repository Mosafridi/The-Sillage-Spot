/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function () {
  // Tab functionality
  const tabButtons = document.querySelectorAll(".tab-button");
  const tabContents = document.querySelectorAll(".tab-content");

  tabButtons.forEach((button) => {
    button.addEventListener("click", () => {
      const targetTab = button.getAttribute("data-tab");

      // Remove active class from all buttons and contents
      tabButtons.forEach((btn) => btn.classList.remove("active"));
      tabContents.forEach((content) => content.classList.remove("active"));

      // Add active class to the clicked button and corresponding content
      button.classList.add("active");
      document.getElementById(targetTab).classList.add("active");
    });
  });

  // Post submission
  document.getElementById("create-post-form").addEventListener("submit", async function (event) {
    event.preventDefault();

    const content = document.getElementById("content").value;
    const imageInput = document.getElementById("image");
    const imageFile = imageInput.files[0];

    if (!content || !imageFile) {
      alert("Please fill in all fields!");
      return;
    }

    const formData = new FormData();
    formData.append("content", content);
    formData.append("image", imageFile);

    try {
      const response = await fetch("/api/create-post", {
        method: "POST",
        body: formData,
      });

      if (!response.ok) {
        throw new Error("Failed to create post.");
      }

      const postData = await response.json();

      // Dynamically add the post to the current tab
      const currentTab = document.querySelector(".tab-content.active");
      const postContainer = currentTab.querySelector(".post-container");

      const newPostHTML = `
        <div class="post">
          <img src="${postData.imageUrl}" alt="Post Image" class="post-image">
          <div class="post-info">
            <h3>${postData.title}</h3>
            <p>${postData.content}</p>
            <div class="comments">
              <p>User1: Great post!</p>
            </div>
            <button class="comment-btn">Comment</button>
          </div>
        </div>
      `;

      postContainer.insertAdjacentHTML("afterbegin", newPostHTML);

      // Clear the form
      this.reset();
    } catch (error) {
      console.error(error);
      alert("An error occurred while creating the post.");
    }
  });
});
