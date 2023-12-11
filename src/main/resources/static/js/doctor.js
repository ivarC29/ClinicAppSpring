let myModal;
const showModalBtns = document.querySelectorAll('.show-modal');





// Listeners

document.addEventListener('DOMContentLoaded', async function() {
	myModal = new bootstrap.Modal(document.getElementById("modal-appointment"), {});
});

showModalBtns.forEach( (showModalBtn) => {
    showModalBtn.addEventListener( 'click', () => {
        myModal.show();
    });
});


