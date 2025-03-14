


document.addEventListener('DOMContentLoaded', function() {
    const createPostForm = document.getElementById('create-post-form');
    const commentForms = document.querySelectorAll('.comment-form');

    createPostForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;
        const imageUrl = document.getElementById('image').value;

        fetch('/PostServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `action=createPost&title=${encodeURIComponent(title)}&content=${encodeURIComponent(content)}&imageUrl=${encodeURIComponent(imageUrl)}`
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            // Optionally, clear the form or reload the page
            createPostForm.reset();
        });
    });

    commentForms.forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            const postId = this.closest('.post').getAttribute('data-post-id');
            const commentText = this.querySelector('input[type="text"]').value;

            fetch('/PostServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `action=createComment&postId=${postId}&commentText=${encodeURIComponent(commentText)}`
            })
            .then(response => response.text())
            .then(data => {
                alert(data);
                // Optionally, clear the input or reload the page
                this.querySelector('input[type="text"]').value = '';
            });
        });
    });
});
