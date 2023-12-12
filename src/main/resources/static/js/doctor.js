let myModal;
let doctor;
let appointments;

// ref html
const rowHtmlAppointments = document.getElementById('appointments-section');
// Appointment
const apptId = document.getElementById('appointment-id');
// Diagnosis
const dxId = document.getElementById('diagnosis-id');
const dxDescription = document.getElementById('diagnosis-description');

// Recipe and recipe detail
const recId = document.getElementById('recipe-id');
const recDetailId = document.getElementById('recipe-detail-id');
const recInterval = document.getElementById('recipe-interval')
const recDosage = document.getElementById('recipe-dosage')

const recipeDetailTableBody = document.querySelector('#recipe-table tbody');

// Medicine for recipe
const medId = document.getElementById('medicine-id');
const medName = document.getElementById('medicine-name');

// modal
const modalLable = document.getElementById('modal-label');
const modalAppointmentId = document.getElementById('modal-appointment-id');
const modalAppointmentDate = document.getElementById('modal-appointment-date');
const btnSave = document.querySelector('.btn-save');

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

const getDoctorAppointments = async() => {
    const id = 11;
	const res = await fetch(`/doctor/get/${id}`);
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
            <div class="card">
                <img src="/images/appointment.jpg" class="card-img-top" alt="appointment image">
                <div class="card-body">
                    <h5 class="card-title">${appointment.patient.patientName}</h5>
                    <p class="card-text">${appointment.appointmentDate}</p>
                    <p class="card-text">${(appointment.patient.medicalRecord)? appointment.patient.medicalRecord.allergies: 'Sin historia medica'}</p>
                    <button class="btn btn-primary" onclick="showModalAppointment(${appointment.appointmentId})">Ver cita.</button>
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
    inputMedName.placeholder = 'medicamento';
    inputMedName.value = detail.medicine.medicineName;
    cell.appendChild(inputMedName);

    const inputMedId = document.createElement('input');
    inputMedId.type = 'hidden';
    inputMedId.classList.add('medicine-id');
    inputMedId.value = detail.medicine.medicineId;
    cell.appendChild(inputMedId);

    row.appendChild(cell);

    cell = document.createElement('td');

    const inputTime = document.createElement('input');
    inputTime.type = 'time'
    inputTime.classList.add('form-control', 'form-control-sm', 'recipe-interval');
    inputTime.value = detail.interval;
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
    dxId.value = appointment.diagnosis.diagnosisId;
    dxDescription.value = (appointment.diagnosis.description) ? appointment.diagnosis.description: 'Agregue diagnostico' ;

    recId.value = appointment.recipe.recipeId;

    renderRecipeDetail(appointment.recipe.recipeDetails);

    myModal.show();
};

const saveDiagnosis = async() => {
    const diagnosisObj = {
        diagnosisId: dxId.value,
        appointment:{
            appointmentId: apptId.value
        },
        description: dxDescription.value
    }

    fetch('/diagnosis/add', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(diagnosisObj)
	}).then( res => {
		if ( !res.ok ) throw new Error('Error al guardar dianosis !.');
		renderRecipeDetail();
	}).catch( console.log );

}

const saveRecipeDetail = async() => {
    const recipeDetailObj = {
        recipeDetailId: recDetailId.value,
        medicine: {
            medicineId: medId.value,
            medicineName: medName.value
        },
        interval: recInterval.value,
        dosage: recDosage.value
    }

    fetch('/recipe/detail/add', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(recipeDetailObj)
	}).then( res => {
		if ( !res.ok ) throw new Error('Error al guardar recipe detail !.');
		renderRecipeDetail();
	}).catch( console.log );
}

const saveAppointmentContent = async() => {
    await saveDiagnosis();

    

    await saveRecipeDetail();
    myModal.hide();
    swal('Guardado!', 'Guardado con exito !', 'success');
}

// Listeners
document.addEventListener('DOMContentLoaded', async function() {
	myModal = new bootstrap.Modal(document.getElementById("modal-appointment"), {});
    renderCardAppointments();
});

btnSave.addEventListener( 'click', saveAppointmentContent );


