function loadMovies(type = "") {

    let search = document.getElementById("searchBox").value;

    let url = "/movies?";

    if (type !== "") {
        url += "type=" + encodeURIComponent(type) + "&";
    }

    if (search !== "") {
        url += "search=" + encodeURIComponent(search);
    }

    fetch(url)
        .then(response => response.json())
        .then(movies => {

            let movieList = document.getElementById("movieList");

            movieList.innerHTML = "";

            if (movies.length === 0) {
                movieList.innerHTML = "<p class='text-gray-400 col-span-3 text-center'>No movies or series found.</p>";
                return;
            }

            for (let movie of movies) {

                movieList.innerHTML += `
                    <div class="bg-gray-800 p-4 rounded-xl border border-gray-700 shadow-xl flex flex-col justify-between">
                        <div>
                            <!-- Movie Poster -->
                            ${movie.posterUrl ? `<img src="${movie.posterUrl}" alt="${movie.name}" class="w-full h-64 object-cover rounded-lg mb-3">` : ''}
                            
                            <!-- Title & Metadata -->
                            <h2 class="text-lg font-bold text-white">${movie.name}</h2>
                            <p class="text-xs text-gray-400">${movie.type} | ${movie.genre} | ${movie.year}</p>
                            
                            <!-- Description -->
                            <p class="text-xs text-gray-300 mt-2 line-clamp-2">${movie.description || ''}</p>
                        </div>

                        <!-- Rating & Badge Footer -->
                        <div class="mt-4 flex items-center justify-between border-t border-gray-700/60 pt-3">
                            <span class="text-yellow-400 font-bold text-sm">⭐ ${movie.rating ? movie.rating : 'N/A'} / 5</span>
                            <span class="text-xs bg-red-600 px-2.5 py-1 rounded-md text-white font-medium">${movie.type}</span>
                        </div>
                    </div>
                `;
            }
        });
}

// Automatically load all movies when the page opens
loadMovies();

// Optional: Add live search listener if your search box triggers as you type
document.getElementById("searchBox").addEventListener("input", () => {
    loadMovies();
});