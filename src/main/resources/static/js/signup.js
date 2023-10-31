const form = document.querySelector('form');

form.addEventListener( 'submit', async(event) => {
        event.preventDefault();
        
        const formData = new FormData( form );
        let userDto = {};

        for ( const [key, value] of formData) {
			userDto[key] = value;
        }
        
        console.log(userDto);
        
        fetch("/user/signup", {
			method: 'POST',
	        body: JSON.stringify(userDto),
	        headers: {
	            'Content-Type': 'application/json'
	        }
		}).then( res => {
			if ( !res.ok ) throw new Error('Error al guardar usuario paciente !')
			form?.reset();
		}).catch( console.log )

})