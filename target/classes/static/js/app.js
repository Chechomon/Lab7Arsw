app = (function () {

    return{
        getCinemaByName: function (name) {
            return apiclient.getCinemaByName(name, function (cine) {
                var table = $("#cinema");
                for (var i = 0; i < cine[0].functions.length; i++) {
                    table.append("<tr><td>" + cine[0].name + "</td><td>" + cine[0].functions[i].movie.name + "</td><td>" + cine[0].functions[i].seats.length + "</td><td>" + cine[0].functions[i].date + "</td>")
                }
                console.log(cine);
            });
        }
    }

})();