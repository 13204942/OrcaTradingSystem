var portfolioList = document.createElement('ol');
var viewMoreDiv = document.createElement('div');
var viewButton = document.getElementById('viewPortfolioButton');
var counter = 0;
var minPortfolios = 0;
var maxPortfolios = 3;

function displayPortfolioList(divName, portfolios) {
    counter++;
    viewButton.innerHTML = "Hide Portfolios";

    if (counter % 2 != 0) {

        $.each(portfolios, function (key, portfolio) {

            if (portfolio.id > minPortfolios && portfolio.id <= maxPortfolios) {
                portfolioList.innerHTML += "<li>" + portfolio.name + "</li>";
            }

        });

        viewMoreDiv.innerHTML = "<button id = 'viewMore' name='viewMoreButton' onclick='displayPortfolioList(" + divName + "," + portfolios + ");'>View More</button>";
        document.getElementById(divName).appendChild(portfolioList);
        document.getElementById(divName).appendChild(viewMoreDiv);

    } else {

        hidePortfolioList();
    }

    minPortfolios += 3;
    maxPortfolios += 3;

}

function hidePortfolioList() {

    viewButton.innerHTML = "View Portfolios";
    portfolioList.parentNode.removeChild(portfolioList);
    viewMoreDiv.parentNode.removeChild(viewMoreDiv);

}

function getPortfolios(divName) {

    $.ajax({
        type: "GET",
        url: "rest/orcahome",
        cache: false,

        success: function (data) {
            displayPortfolioList(divName, data);
        },

        error: function (xhr, message, errorThrown) {
            alert("Ajax error occurred: " + message);
        }
    });
}

function createPortfolio() {

}

function clearForm() {
    var title = document.getElementById('title');
    var genre = document.getElementById('genre');
    var rating = document.getElementById('rating');
    var description = document.getElementById('description');
    title.value = '';
    genre.value = '';
    rating.value = '';
    description.value = '';
}

