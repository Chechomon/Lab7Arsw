app = (function () {

    return{
        getCinemaByName: function (name) {
            return apiclient.getCinemaByName(name, function (cine) {
                for (var i = 0; i < cine.functions.length; i++) {
                    $("#tabla").append("<tr><td>" + cine.name + "</td><td>" + cine.functions[i].movie.name + "</td><td>" + cine.functions[i].seats.length + "</td><td>" + cine.functions[i].date + "</td>")
                }
                //console.log(cine);
            });
        }
    }

})();