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
                movieList.innerHTML = "<p>No movies or series found.</p>";
                return;
            }

            for (let movie of movies) {

                movieList.innerHTML += `
                    <div>
                        <h2>${movie.name}</h2>
                        <p>${movie.type} | ${movie.genre} | ${movie.year}</p>
                    </div>
                `;
            }
        });
}

loadMovies();