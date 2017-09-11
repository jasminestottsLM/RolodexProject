const baseurl = 'http://localhost:14400/cards';

function fillInDetails(data) {
	let html = `
		<h1>${data.first_name} ${data.last_name}</h1>
		<h2>Title: ${data.person_title}</h2>
		<div>Company: ${data.person_co}</div> 
	`;
	
	for (let phone of data.phoneNumbers) {
		html += `
		<div>
			<b>${phone.phone_number}</b>
			<div>Phone Type: ${phone.phone_type}</div>
		</div>
		`;
	}
	
	for (let address of data.addresses) {
		html += ` 
		<div>
			<b>${address.address_type}</b>
			<div>${address.address_street}</div>
			<div>${address.address_city}</div>
			<div>${address.address_state}</div>
			<div>${address.address_zip}</div>
		</div>
		`;
	}
	
	html += `
		<form id="create-phone-form" method="post" action="/cards/${data.id}/phones">
			<input name="phone_type" id="phone_type" placeholder="Phone Type">
			<br><br>
			<input name="phone_number" id="phone_number" placeholder="(###) ###-####">
			<br><br>
			<button>Add Phone Number</button>
		</form>
		<br><br>
	 `;	
	
	html += `
		<form id="create-address-form" method="post" action="/cards/${data.id}/addresses">
			<input name="address_type" id="address_type" placeholder="Address Type">
			<br><br>
			<input name="address_street" id="address_street" placeholder="Street Address">
			<br><br>
			<input name="address_city" id="address_city" placeholder="City">
			<br><br>
			<input name="address_state" id="address_state" placeholder="State">
			<br><br>
			<input name="address_zip" id="address_zip" placeholder="Zip Code">
			<br><br>
			<button>Add Address</button>
		</form>
	 `;	
		
	$('#card-detail').html(html);
}


function createListElement (card) {
	$('<li></li>')
	.html(`
		<a href="#" data-card-id="${card.id}">
			${card.last_name}, ${card.first_name}
		</a>
		<form class="delete-card-form" method="post" action="/cards/${card.id}">
			<button>Delete</button>	
		</form>
	`)
	.appendTo($('#card-list'));
} 


$(document).on('submit', '.delete-card-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this)
				.closest('li')
				.remove();
		})
		.fail(error => console.error(error));
});


$('#add-entry-form').on('submit', function (e) {
	e.preventDefault();
	
	let payload = {
		first_name: $('#first_name').val(),
		last_name: $('#last_name').val(),
		person_title: $('#person_title').val(),
		person_co: $('#person_co').val(),
		picture: $('#picture').val()
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done((card) => {
			createListElement(card);
		})
		.fail(error => console.error(error));	
});

$(document).on('submit', '#create-phone-form', function (e) {
    e.preventDefault();
    
    let payload = {
            phone_type: $('#phone_type').val(),
            phone_number: $('#phone_number').val()
        };
        
        let ajaxOptions = {
            type: 'POST',
            data: JSON.stringify(payload),
            contentType: 'application/json'
        };
        
        $.ajax(this.action, ajaxOptions)
            .done(function (data) {
            	fillInDetails(data);
            })
            .fail(error => console.error(error));
});


$(document).on('submit', '#create-address-form', function (e) {
    e.preventDefault();
    
    let payload = {
            address_type: $('#address_type').val(),
            address_street: $('#address_street').val(),
            address_city: $('#address_city').val(),
            address_state: $('#address_state').val(),
            address_zip: $('#address_zip').val()
        };
        
        let ajaxOptions = {
            type: 'POST',
            data: JSON.stringify(payload),
            contentType: 'application/json'
        };
        
        $.ajax(this.action, ajaxOptions)
            .done(function (data) {
            	fillInDetails(data);
            })
            .fail(error => console.error(error));
});


$(document).on('click', 'a[data-card-id]', function (e) {
	e.preventDefault();
	const cardId = $(this).data('cardId');
	
	$.getJSON(baseurl + '/' + cardId, function (data) {
		data.person_co = data.person_co || '<i>no company specified</i>';
		fillInDetails(data);
		
	});
});


$.getJSON(baseurl, function (data) { 
	if (data.length) {
		for (let card of data) {
			createListElement(card);
		}
	} else {
		createListElement(card);
	}
});
