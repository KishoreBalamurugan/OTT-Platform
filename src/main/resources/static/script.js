let currentType = "";

function loadMovies(type = currentType) {

    currentType = type;

    let search = document.getElementById("searchBox").value;

    let url = "/movies?";

    if (type !== "") {
        url += "type=" + encodeURIComponent(type) + "&";
    }

    if (search !== "") {
        url += "search=" + encodeURIComponent(search);
    }

    updateFilterButtons();

    fetch(url)
        .then(response => response.json())
        .then(movies => {

            let movieList = document.getElementById("movieList");

            movieList.innerHTML = "";

            if (movies.length === 0) {
                movieList.innerHTML =
                    "<p class='text-gray-400 col-span-3 text-center'>No movies or series found.</p>";
                return;
            }

            for (let movie of movies) {

                let poster = movie.posterUrl;

                movieList.innerHTML += `
                    <div class="bg-gray-800 p-4 rounded-xl border border-gray-700 shadow-xl flex flex-col justify-between hover:border-red-500 transition">

                        <div>

                            <img
                                src="${poster || 'https://via.placeholder.com/300x450?text=No+Poster'}"
                                alt="${movie.name}"
                                class="w-full h-64 object-cover rounded-lg mb-3"
                                onerror="this.src='https://via.placeholder.com/300x450?text=No+Poster'"
                            >

                            <h2 class="text-lg font-bold text-white">
                                ${movie.name}
                            </h2>

                            <p class="text-xs text-gray-400">
                                ${movie.type} | ${movie.genre} | ${movie.year}
                            </p>

                            <p class="text-xs text-gray-300 mt-2 line-clamp-2">
                                ${movie.description || ''}
                            </p>

                        </div>

                       <div class="mt-4 border-t border-gray-700/60 pt-3">

    <div class="flex items-center justify-between mb-3">

        <span class="text-yellow-400 font-bold text-sm">
            ⭐ ${movie.rating ? movie.rating : 'N/A'} / 5
        </span>

        <span class="text-xs bg-red-600 px-2.5 py-1 rounded-md text-white font-medium">
            ${movie.type}
        </span>

    </div>

    <button onclick="addToWatchlist(${movie.id})"
            class="w-full bg-gray-700 hover:bg-red-600
                   text-white py-2 rounded-lg text-sm font-medium transition">

        + Add to Watchlist

    </button>

</div>

                    </div>
                `;
            }
        })
        .catch(error => {
            console.error("Error loading movies:", error);

            document.getElementById("movieList").innerHTML =
                "<p class='text-red-400 col-span-3 text-center'>Unable to load movies.</p>";
        });
}

function addToWatchlist(movieId) {

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Please login first.");
        window.location.href = "/login.html";
        return;
    }

    fetch("/api/watchlist/add/" + movieId, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token
        }
    })

        .then(response => response.text())

        .then(message => {

            alert(message);

        })

        .catch(error => {

            console.error(error);
            alert("Failed to add movie to watchlist.");

        });
}

function updateFilterButtons() {

    let allBtn = document.getElementById("allBtn");
    let movieBtn = document.getElementById("movieBtn");
    let seriesBtn = document.getElementById("seriesBtn");

    // Reset all buttons
    allBtn.className =
        "filter-btn bg-gray-800 border border-gray-700 px-4 py-2 rounded hover:bg-gray-700 transition";

    movieBtn.className =
        "filter-btn bg-gray-800 border border-gray-700 px-4 py-2 rounded hover:bg-gray-700 transition";

    seriesBtn.className =
        "filter-btn bg-gray-800 border border-gray-700 px-4 py-2 rounded hover:bg-gray-700 transition";

    // Highlight selected button
    if (currentType === "") {

        allBtn.className =
            "filter-btn bg-red-600 border border-red-500 px-4 py-2 rounded transition";

    } else if (currentType === "Movie") {

        movieBtn.className =
            "filter-btn bg-red-600 border border-red-500 px-4 py-2 rounded transition";

    } else if (currentType === "Series") {

        seriesBtn.className =
            "filter-btn bg-red-600 border border-red-500 px-4 py-2 rounded transition";
    }
}


// Load all movies when page opens
loadMovies();


// Live search
document.getElementById("searchBox").addEventListener("input", () => {
    loadMovies();
});