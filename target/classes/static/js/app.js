let palavraSelecionada = "";

document.querySelectorAll(".word").forEach(word => {

    word.addEventListener("click", function () {

        palavraSelecionada = this.dataset.word;

        document.getElementById("tituloPalavra").innerText = palavraSelecionada;

        // Limpa a seleção anterior
        document.querySelectorAll("input[name='status']")
            .forEach(radio => radio.checked = false);

    });

});

document.getElementById("btnSalvar").addEventListener("click", async () => {

    const radioSelecionado = document.querySelector("input[name='status']:checked");

    if (!radioSelecionado) {
        alert("Selecione uma familiaridade.");
        return;
    }

    const status = radioSelecionado.value;

    try {

        const response = await fetch("/palavras/status", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                palavra: palavraSelecionada,
                status: status
            })

        });

        if (response.ok) {

            const modal = bootstrap.Modal.getInstance(
                document.getElementById("wordModal")
            );

            modal.hide();

        } else {

            alert("Erro ao salvar a familiaridade.");

        }

    } catch (e) {

        console.error(e);

        alert("Erro ao comunicar com o servidor.");

    }

});