let allMovies = [];
let filteredMovies = [];
let currentGenreFilter = '';

// Create movie card HTML
function createMovieCard(movie) {
    const stars = '★'.repeat(Math.round(movie.rating / 2)) + '☆'.repeat(5 - Math.round(movie.rating / 2));
    const badge = movie.status === 'NOW_SHOWING'
        ? '<span class="bg-red-600 text-white text-xs px-2 py-1 rounded-full font-bold">NOW SHOWING</span>'
        : '<span class="bg-yellow-600 text-white text-xs px-2 py-1 rounded-full font-bold">COMING SOON</span>';

    return `
    <div class="movie-card bg-gray-900 rounded-xl overflow-hidden shadow-lg cursor-pointer transform hover:-translate-y-2 transition-all duration-300"
         onclick="window.location.href='pages/movie-detail.html?id=${movie.id}'">
        <div class="relative">
            <img src="${movie.posterUrl || 'https://via.placeholder.com/300x450/1a1a2e/e50914?text=' + encodeURIComponent(movie.title)}"
                 alt="${movie.title}"
                 class="w-full h-64 object-cover"
                 onerror="this.src='https://via.placeholder.com/300x450/1a1a2e/e50914?text=${encodeURIComponent(movie.title)}'">
            <div class="absolute top-2 left-2">${badge}</div>
            <div class="movie-overlay absolute inset-0 bg-black bg-opacity-75 opacity-0 flex items-center justify-center">
                <button class="bg-red-600 hover:bg-red-700 text-white px-6 py-3 rounded-lg font-bold">
                    <i class="fas fa-ticket-alt mr-2"></i> Book Now
                </button>
            </div>
        </div>
        <div class="p-4">
            <h3 class="font-bold text-sm truncate">${movie.title}</h3>
            <div class="flex items-center justify-between mt-1">
                <span class="text-gray-500 text-xs">${movie.genre || 'N/A'}</span>
                <span class="text-yellow-400 text-xs">${movie.rating || '?'}/10</span>
            </div>
            <div class="flex items-center justify-between mt-1">
                <span class="text-gray-500 text-xs">${movie.language || 'English'}</span>
                <span class="text-gray-500 text-xs">${movie.durationMinutes ? movie.durationMinutes + ' min' : ''}</span>
            </div>
        </div>
    </div>`;
}

// Load and render movies
async function loadMovies() {
    const grid = document.getElementById('moviesGrid');
    const upcomingGrid = document.getElementById('upcomingGrid');

    // Fetch from backend
    let movies = await MovieAPI.getAll();

    if (!movies || movies.length === 0) {
        movies = [];
        console.info('No movies found in database');
    }

    allMovies = movies;
    filteredMovies = movies;

    const nowShowing = movies.filter(m => m.status === 'NOW_SHOWING');
    const comingSoon = movies.filter(m => m.status === 'COMING_SOON');

    if (grid) {
        if (nowShowing.length === 0) {
            grid.innerHTML = '<div class="text-center col-span-full py-8 text-gray-500">No movies currently showing</div>';
        } else {
            grid.innerHTML = nowShowing.map(createMovieCard).join('');
        }
    }

    if (upcomingGrid) {
        upcomingGrid.innerHTML = comingSoon.length > 0
            ? comingSoon.map(createMovieCard).join('')
            : '<div class="text-gray-500 col-span-full">No upcoming movies</div>';
    }

    // Update count
    const countEl = document.getElementById('movieCount');
    if (countEl) countEl.textContent = movies.length + '+';
}

// Filter movies by search input
function filterMovies() {
    const query = document.getElementById('searchInput')?.value.toLowerCase() || '';
    const movies = allMovies.filter(m =>
        (m.title?.toLowerCase().includes(query) ||
            m.genre?.toLowerCase().includes(query) ||
            m.director?.toLowerCase().includes(query)) &&
        (currentGenreFilter === '' || m.genre === currentGenreFilter)
    );

    const grid = document.getElementById('moviesGrid');
    if (grid) {
        grid.innerHTML = movies.length > 0
            ? movies.map(createMovieCard).join('')
            : '<div class="text-center col-span-full py-8 text-gray-500">No movies found</div>';
    }
}

function filterByGenre(genre) {
    currentGenreFilter = genre;
    document.querySelectorAll('.genre-btn').forEach(btn => {
        btn.classList.remove('bg-red-600', 'text-white');
        btn.classList.add('bg-gray-800', 'text-gray-300');
    });
    event.target.classList.add('bg-red-600', 'text-white');
    event.target.classList.remove('bg-gray-800', 'text-gray-300');
    filterMovies();
}

// Init
document.addEventListener('DOMContentLoaded', () => {
    loadMovies();
});
