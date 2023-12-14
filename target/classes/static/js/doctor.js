let myModal;
let doctor;
let appointments;
let medList;

// ref html
const rowHtmlAppointments = document.getElementById('appointments-section');
// Appointment
const apptId = document.getElementById('appointment-id');

// Diagnosis
const dxId = document.getElementById('diagnosis-id');
const dxDescription = document.getElementById('diagnosis-description');

// Recipe
const recId = document.getElementById('recipe-id');
const recipeDetailTableBody = document.querySelector('#recipe-table tbody');

// modal
const modalLable = document.getElementById('modal-label');
const modalAppointmentId = document.getElementById('modal-appointment-id');
const modalAppointmentDate = document.getElementById('modal-appointment-date');
const btnSave = document.querySelector('.btn-save');

const medDatalist = document.getElementById('med-list-data');

//json
const emptyDetail = {
    recipeDetailId: 0,
    medicine:{
        medicineId: 0,
        medicineName: ''
    },
    interval: '00:00:00',
    dosage: 0
}

const getMeds = async() => {
	if (medList) return;
	
	const res = await fetch(`/medicine/toList`);
	const data = await res.json();
	medList = data;
}

const fillMedDataList = async() => {
	await getMeds();
	medDatalist.innerHTML = '';
	medList.forEach( med => {
	    const option = document.createElement('option');
	    option.value = med.medicineName;
	    medDatalist.appendChild(option);
  	});
}

const getDoctorAppointments = async() => {
	// const res = await fetch(`/doctor/get/${id}`);
    const res = await fetch('/doctor/get');
	const data = await res.json();

	return data;
}

const renderCardAppointments = async() => {
    if (!doctor)
        doctor = await getDoctorAppointments();

    appointments = [];
    rowHtmlAppointments.innerHTML = '';
    doctor.appointments.forEach( (appointment) => {
        appointments.push(appointment);
        const col = `
        <div class="col-sm-10 col-md-6 col-lg-4 col-xl-3 mb-3">
            <div class="card shadow p-2">
                <img src="/images/${Math.round(Math.random()) + 1}.jpg" class="card-img-top" alt="appointment image">
                <div class="card-body">
                    <h5 class="card-title">${appointment.patient.patientName}</h5>
                    <p class="card-text text-center fs-5"><span class="badge text-bg-info text-light">${appointment.appointmentDate}</span></p>
                    <p class="card-text">${(appointment.patient.medicalRecord)? appointment.patient.medicalRecord.allergies: 'Sin historia medica'}</p>
                    <div class="d-grid gap-2 col-6 mx-auto">
                        <button class="btn btn-outline-primary" onclick="showModalAppointment(${appointment.appointmentId})">Ver cita</button>
                    </div>
                </div>
            </div>
        </div>
        `
        rowHtmlAppointments.innerHTML += col;
    });
}

const updateListener = () => {
    const detailInputs = document.querySelectorAll('.medicine-name');

    if ( detailInputs.length === 0 ) return;

    const lastInput = detailInputs[detailInputs.length-1];
    if (detailInputs.length > 1 ) {
        const oldLastInput = detailInputs[detailInputs.length-2];
        oldLastInput.removeEventListener( 'input', eventHandler );
    }

    lastInput.addEventListener('input', eventHandler );

}

const eventHandler = () => {
    const detailInputs = document.querySelectorAll('.medicine-name');
    newDetailRow(emptyDetail, detailInputs.length);
}

const renderRecipeDetail = ( recipeDetails ) => {
    recipeDetailTableBody.innerHTML = '';
    let i = 0;
    if (recipeDetails.length !== 0) {
        recipeDetails.forEach( (detail, index) => {
            newDetailRow(detail, index);
            i=index+1;
        });
    }
    newDetailRow(emptyDetail, i);
}

const newDetailRow = (detail, index) => {
    const row = document.createElement('tr');

    let cell = document.createElement('td');
    cell.classList.add('text-center');
    cell.innerText = index  + 1;

    const inputDetId = document.createElement('input');
    inputDetId.type = 'hidden';
    inputDetId.classList.add('recipe-detail-id');
    inputDetId.value = detail.recipeDetailId;
    cell.appendChild(inputDetId);

    row.appendChild(cell);

    cell = document.createElement('td');

    const inputMedName = document.createElement('input');
    inputMedName.type = 'text';
    inputMedName.classList.add('form-control', 'form-control-sm', 'medicine-name');
    inputMedName.setAttribute('list', 'med-list-data');
    inputMedName.placeholder = 'medicamento';
    inputMedName.value = detail.medicine.medicineName;
    
    
    const inputMedId = document.createElement('input');
    inputMedId.type = 'hidden';
    inputMedId.classList.add('medicine-id');
    inputMedId.value = detail.medicine.medicineId;
    
    inputMedName.addEventListener('input', () => {
        const selectedName = inputMedName.value;
        const selectedMedicine = medList.find(medicine => medicine.medicineName === selectedName);
        if (selectedMedicine) {
            inputMedId.value = selectedMedicine.medicineId;
        } else {
            inputMedId.value = '0';
        }
    });

    cell.appendChild(inputMedId);
    cell.appendChild(inputMedName);
    row.appendChild(cell);

    cell = document.createElement('td');

    const inputTime = document.createElement('input');
    inputTime.type = 'time'
    inputTime.classList.add('form-control', 'form-control-sm', 'recipe-interval');
    inputTime.value = (detail.interval) ? detail.interval: '00:00:00';
    cell.appendChild(inputTime);

    row.appendChild(cell);

    cell = document.createElement('td');

    const inputDosage = document.createElement('input');
    inputDosage.type = 'number'
    inputDosage.classList.add('form-control', 'form-control-sm', 'recipe-dosage');
    inputDosage.value = detail.dosage;
    cell.appendChild(inputDosage);

    row.appendChild(cell);
    recipeDetailTableBody.appendChild(row);
    updateListener();
}

const showModalAppointment = ( appointmentId ) => {
    apptId.value = appointmentId;
    const appointment = appointments.find(appoint => appoint.appointmentId === appointmentId);
    modalLable.innerText = appointment.patient.patientDNI + ' - ' + appointment.patient.patientName;
    modalAppointmentId.innerText = appointment.appointmentId;
    modalAppointmentDate.innerText = appointment.appointmentDate;
    console.log(appointment);
    dxId.value = (appointment.diagnosis) ? appointment.diagnosis.diagnosisId : appointment.appointmentId;
    dxDescription.value = (appointment.diagnosis) ? appointment.diagnosis.description: 'Agregue diagnostico' ;

    recId.value = (appointment.recipe) ? appointment.recipe.recipeId : appointment.appointmentId;

    renderRecipeDetail((appointment.recipe) ? appointment.recipe.recipeDetails: []);

    myModal.show();
};

const getDiagnosis = () => {

    const diagnosisObj = {
        diagnosisId: parseInt(dxId.value),
        description: dxDescription.value,
        appointment: {
            appointmentId: parseInt(dxId.value)
        }
    };

    return diagnosisObj;

}

const getRecipe = () => {

    const recipeObj = {
        recipeId: parseInt(recId.value),
        recipeDate: new Date().toISOString().split('T')[0],
        appointment: {
            appointmentId: parseInt(recId.value)
        }
    };

    return recipeObj;

}

const toArrayRecDetail = () => {
    let recDetailArray = [];

    const recDetailIds = document.querySelectorAll('.recipe-detail-id');
    const recIntervals = document.querySelectorAll('.recipe-interval');
    const recDosages = document.querySelectorAll('.recipe-dosage');
    const medIds = document.querySelectorAll('.medicine-id');
    const medNames = document.querySelectorAll('.medicine-name');

    medNames.forEach( ( medName, index ) => {
        if (!medName.value) return;

        const recipeDetailObj = {
        recipeDetailId: recDetailIds[index].value,
        medicine: {
            medicineId: medIds[index].value,
            medicineName: medName.value
        },
        recipe: {
            recipeId:  recId.value
        },
        interval: ( recIntervals[index].value.length === 8 ) ? recIntervals[index].value: recIntervals[index].value + ':00',
        dosage: recDosages[index].value
        }
        recDetailArray.push(recipeDetailObj);
    });

    return recDetailArray;

}

const saveDiagnosis = async( diagnosisObj ) => {

    return await fetch('/diagnosis/add', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(diagnosisObj)
	}).then( res => {
		if ( !res.ok ) {
            throw new Error('Error al guardar dianosis !.')
        } else {
            return 1;
        }

	}).catch( console.log );

}

const saveRecipe = async( recipeObj ) => {
    return await fetch('/recipe/add', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(recipeObj)
	}).then( res => {
		if ( !res.ok ) {
            throw new Error('Error al guardar recipe !.');
        } else {
            return 1;
        }
	}).catch( console.log );
}

const saveRecipeDetail = async( recipeDetailObj ) => {

    return await fetch('/recipe/detail/add', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(recipeDetailObj)
	}).then( res => {
		if ( !res.ok ) {
            throw new Error('Error al guardar recipe detail !.');
        } else {
            return 1;
        }
	}).catch( console.log );
}

const saveAppointmentContent = async() => {

    const diagnosisObj = getDiagnosis();

    const resSaveDiagnosis = await saveDiagnosis(diagnosisObj);
    if ( resSaveDiagnosis !== 1 ) return;

    const recipeObj = getRecipe();
    const resSaveRecipe = await saveRecipe(recipeObj);
    if ( resSaveRecipe !== 1 ) return;

    const recDetailArray = toArrayRecDetail();
    recDetailArray.forEach( async(recipeDetailObj) => {
        const resSaveRecDetail =  await saveRecipeDetail(recipeDetailObj);
        if ( resSaveRecDetail !== 1 ) return;
    });

    myModal.hide();
    swal('Guardado!', 'Guardado con exito !', 'success');
}

// Listeners
document.addEventListener('DOMContentLoaded', async function() {
	myModal = new bootstrap.Modal(document.getElementById("modal-appointment"), {});
    renderCardAppointments();
    fillMedDataList();
});

btnSave.addEventListener( 'click', saveAppointmentContent );


