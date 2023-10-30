const form = document.querySelector('form');

form.addEventListener( 'submit', async(event) => {
        event.preventDefault();
        
        const formData = new FormData( form );
        let userDTO = {};

        for ( const [key, value] of formData) {
			userDTO[key] = value;
        }
        
        console.log(userDTO);

})