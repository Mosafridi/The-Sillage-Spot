// 1. Search Bar Functionality
const searchBar = document.querySelector('.search-bar input');
const searchButton = document.querySelector('.search-bar button');

searchButton.addEventListener('click', () => {
  const query = searchBar.value.trim();
  if (query) {
    alert(`You searched for: ${query}`);
    // Replace this with actual search functionality (e.g., filter perfumes)
  } else {
    alert('Please enter a search term.');
  }
});

// 2. Dynamic Perfume Filtering (Example)
const perfumeItems = document.querySelectorAll('.perfume-item');

document.querySelectorAll('.filters select').forEach(filter => {
  filter.addEventListener('change', () => {
    const brand = document.querySelector('#brand-filter').value.toLowerCase();
    const price = document.querySelector('#price-filter').value;
    const type = document.querySelector('#type-filter').value.toLowerCase();

    perfumeItems.forEach(item => {
      const itemBrand = item.getAttribute('data-brand').toLowerCase();
      const itemPrice = parseFloat(item.getAttribute('data-price'));
      const itemType = item.getAttribute('data-type').toLowerCase();

      const brandMatch = brand === 'all' || itemBrand === brand;
      const priceMatch = price === 'all' || 
        (price === 'under50' && itemPrice < 50) ||
        (price === '50-100' && itemPrice >= 50 && itemPrice <= 100) ||
        (price === 'over100' && itemPrice > 100);
      const typeMatch = type === 'all' || itemType === type;

      if (brandMatch && priceMatch && typeMatch) {
        item.style.display = 'block';
      } else {
        item.style.display = 'none';
      }
    });
  });
});

// 3. Hover Effects for Perfume Items
perfumeItems.forEach(item => {
  item.addEventListener('mouseenter', () => {
    item.style.transform = 'scale(1.05)';
    item.style.transition = 'transform 0.3s ease';
  });
  item.addEventListener('mouseleave', () => {
    item.style.transform = 'scale(1)';
  });
});