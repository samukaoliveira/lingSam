let palavraSelecionada = "";

document.querySelectorAll(".word").forEach(word => {

    word.addEventListener("click", async function () {

        palavraSelecionada = this.dataset.word;

        document.getElementById("tituloPalavra").innerText = palavraSelecionada;

        // Limpa seleção anterior
        document.querySelectorAll("input[name='status']")
            .forEach(radio => radio.checked = false);

        try {

            const response = await fetch(
                "/palavras/status?palavra=" + encodeURIComponent(palavraSelecionada)
            );

            if (response.ok) {

                const dados = await response.json();

                const radio = document.querySelector(
                    `input[name="status"][value="${dados.status}"]`
                );

                if (radio) {
                    radio.checked = true;
                }

            } else {

                console.error("Não foi possível carregar o status.");

            }

        } catch (e) {

            console.error(e);

        }

    });

});

document.getElementById("btnSalvar").addEventListener("click", async () => {

    const radioSelecionado = document.querySelector(
        "input[name='status']:checked"
    );

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

            // Atualiza a classe CSS da palavra na tela
            const span = document.querySelector(
                `.word[data-word="${CSS.escape(palavraSelecionada)}"]`
            );

            if (span) {

                span.classList.remove(
                    "nova",
                    "familiar",
                    "aprendendo",
                    "dominada"
                );

                span.classList.add(status.toLowerCase());

            }

        } else {

            alert("Erro ao salvar a familiaridade.");

        }

    } catch (e) {

        console.error(e);

        alert("Erro ao comunicar com o servidor.");

    }

});