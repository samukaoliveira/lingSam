document.querySelectorAll(".word")
    .forEach(word => {

        word.addEventListener("click", function(){

            document.getElementById("tituloPalavra")
                .innerText = this.dataset.word;

        });

    });