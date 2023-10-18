let orderList =[];


function PostId(id) {

	setTimeout(function() {
		var bookid = id
		var SendInfo = { "id": bookid }
		$.ajax({
			type: 'post',
			url: 'http://localhost:8080/book/id',
			data: JSON.stringify(SendInfo),
			contentType: "application/json; charset=utf-8",
			traditional: true,
			success: function() {
				console.log("post successed")
			}
		})
	}, 50)
};



function AddCart(event,id) {
	var bookurl = 'http://localhost:8080/book/';
	bookurl = bookurl + id;
	if(orderList.some(item => item===id)){
		console.log("duplicate");
		return;
	}
	orderList.push(id);
	$.ajax({
		type: "Get",
		url: "http://localhost:8080/booklist",
		success: function(list) {

			$.ajax({
				type: "Get",
				url: bookurl,
				success: function(data) {
					window.sessionStorage.setItem(id,JSON.stringify(data));
					document.getElementById("cart").classList.remove("cartlist-empty");
					var addhtml = '';
					addhtml +=
						'<div class="cartlist-item" id="' + data.id + '">' +
						'<div>' +
						'<img class="cartlist-item-image" src="' + data.imageURL + '">' +
						'</div>' +

						'<div class="item-info">' +
						'<h2 class="item-name">' + data.name + '</h2>' +
						'<h3>' + data.author + '</h3>' +
						'<h2 style="color:#3f9acd">$' + data.price + ' </h2>' +
						'</div>'
						+ '<button style="height: 17px; z-index:1"; onclick="removeCart(' + data.id + ')"> Delete</button>' +
						'</div>';

					$("#list-item").prepend(addhtml);

				}
			})
		}
	})





};

function AddCartList() {


	document.getElementById("list-item").innerHTML="";

	$.ajax({
		type: "Get",
		url: "http://localhost:8080/booklist",
		success: function(list) {

      var length = Object.keys(list).length;
      if(length===0 || Object.keys(sessionStorage).length===0){
		  document.getElementById("cart").classList.add("cartlist-empty");
	  }
	  else{
		  
	    document.getElementById("cart").classList.remove("cartlist-empty");
          
        var itemlist =Object.keys(sessionStorage);
			for (let i = 0; i < itemlist.length; i++) {
				let data = window.sessionStorage.getItem(itemlist[i]);
				data = JSON.parse(data);
				var addhtml = '';
							addhtml +=
								'<div class="cartlist-item" id="' + data.id + '">' +
								'<div>' +
								'<img class="cartlist-item-image" src="' + data.imageURL + '">' +
								'</div>' +

								'<div class="item-info">' +
								'<h2 class="item-name">' + data.name + '</h2>' +
								'<h3>' + data.author + '</h3>' +
								'<h2 style="color:#3f9acd">$' + data.price + ' </h2>' +
								'</div>'
								+ '<button style="height: 17px;z-index:1"; onclick="removeCart(' + data.id + ')"> Delete</button>' +
								'</div>';

							$("#list-item").prepend(addhtml);					
			}
		}
	}
	})

}

function removeCart(id) {
	id = '' + id;
	window.sessionStorage.removeItem(id);
	var parent = document.getElementById("list-item");
	var child = document.getElementById(id);
	parent.removeChild(child);
	var bookurl = "http://localhost:8080/book/" + id;
	$.ajax({
		type: "DELETE",
		url: bookurl,
		success: function() {
			console.log("delete successed");
		}
	})
	setTimeout(
		function() {
			$.ajax({
				type: "Get",
				url: "http://localhost:8080/booklist",
				success: function(list) {
					if (Object.keys(list).length === 0) {
						document.getElementById("cart").classList.add("cartlist-empty");
					}
				}
			})
		}, 40

	)

}


function AddCheckOutCart() {

	$.ajax({
		type: "Get",
		url: "http://localhost:8080/cartlist",
		success: function(list) {
			
	if(Object.keys(list).length !==0){
		let totalprice = 0;
		for (let i = 0; i < Object.keys(list).length; i++) {
				if (list[i].id !== -1) {
					data=list[i];
					let price = parseFloat(list[i].price) * parseFloat(list[i].quantity);
					price = price.toFixed(2);
					totalprice+= price;
					var addhtml = '';
							addhtml +=
								'<div id="' + data.productId + '">' +
								'<table class="order-table">' +
								'<tbody>' +

								'<tr>' +
								'<td><img src="' + data.imageURL + '" class="full-width"></img>' +
								'</td>' +
								'<td class="item-order">' +
								'<button class="delete-btn" onclick="removeCheckOutCart(' + data.productId + ')"> Delete</button>' +
								'<br> <div class="thin" style="width : 230px">' + data.name + '</div>' +
								'<br>' + data.category + '<br> <span style="font-size: 15px"> By ' + data.author +
								`<div style="display: flex; margin-top: 7px">
                                <span class="material-icons quantity-button" onclick="plus(`+data.productId+`)">add</span>
                                <div class="quantity" style="margin-right: 10px;margin-left: 10px;">`+ data.quantity +`</div>
                                <span class="material-icons quantity-button" onclick="minus(`+data.productId +`)">remove</span>
                                </div>`
								+`</span>`
								+'</td>' +

								' </tr>' +

								'<td>' +
								'<div class="price">$' + price + '</div>' +
								'</td>' +
								'</tr>' +
								'</tbody>' +

								'</table>' +
								'<div class="line"></div>'
								+ '</div>';

							$("#checkoutCart").prepend(addhtml);
					
				}

			}
			console.log(totalprice);
			document.getElementById("totalprice").textContent='$'+totalprice.toFixed(2);
			
		}
		}
	})

}
function removeCheckOutCart(id) {
	id = '' + id;
	var bookurl = "http://localhost:8080/book/" + id;
	window.sessionStorage.removeItem(id);
	$.ajax({
		type: "delete",
		url: bookurl,
		success: function() {
			console.log("delete successed");
		}
	})
	var parent = document.getElementById("checkoutCart");
	var child = document.getElementById(id);
	parent.removeChild(child);


	UpdateCart();




}

function UpdateCart(){
	setTimeout( function(){
			$.ajax({
				type: "Get",
				url: "http://localhost:8080/cartlist",
				success: function(list) {

					if(Object.keys(list).length !==0){
						var totalprice =0 ;
						for (let i = 0; i < Object.keys(list).length; i++) {
							totalprice+= parseFloat(list[i].price) * parseFloat(list[i].quantity);
						}
						console.log(totalprice);
						document.getElementById("totalprice").textContent='$'+totalprice.toFixed(2);

					}
					else{
						document.getElementById("totalprice").textContent='$'+0;
					}

				}
			})
		}, 20
	)
}

function plus(id){
	const url = 'http://localhost:8080/item/add/'+id;


	$.ajax({
		type:"GET",
		url: url,
		success: function (data) {
			const quantity = Object.keys(data)[0];
			const price = Object.values(data)[0];
			document.getElementById(id).getElementsByClassName("quantity")[0].innerHTML=" "+quantity+" ";
			document.getElementById(id).getElementsByClassName("price")[0].innerHTML= "$ " + price*quantity;

			UpdateCart();

		}
		}

	)

}


function minus(id){
	const url = 'http://localhost:8080/item/remove/'+id;

	$.ajax({
			type:"GET",
			url: url,
			success: function (data) {
				const quantity = Object.keys(data)[0];
				const price = Object.values(data)[0];
				document.getElementById(id).getElementsByClassName("quantity")[0].innerHTML=" "+quantity+" ";
				document.getElementById(id).getElementsByClassName("price")[0].innerHTML= "$ " + price*quantity;


				console.log(quantity);


				if(quantity === '0'){
					window.sessionStorage.removeItem(id);
					document.getElementById(id).remove();

				}

				UpdateCart();

			}
		}

	)

}

function checkout(){
	window.sessionStorage.clear();
	alert("Thank You")
}
