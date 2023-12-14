const form = document.querySelector('form');

form.addEventListener( 'submit', async(event) => {
        event.preventDefault();
        
        const formData = new FormData( form );
        let userDto = {};

        for ( const [key, value] of formData) {
			userDto[key] = value;
        }
        
        fetch("/user/signup", {
			method: 'POST',
	        body: JSON.stringify(userDto),
	        headers: {
	            'Content-Type': 'application/json'
	        }
		}).then( res => {
			if ( !res.ok ) throw new Error('Error al guardar usuario paciente !')
			swal('Bienvenido!', 'Usuario registrado con exito !', 'success');

			setTimeout(() => {
				window.location.href = '/login';
			}, 1100);
			form?.reset();
		}).catch( console.log )

});