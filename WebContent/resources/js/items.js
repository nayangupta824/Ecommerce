window.onscroll = function() {scrollFunction()};

function scrollFunction() {
	//alert("hi");
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("myBtn").style.display = "block";
    } else {
        document.getElementById("myBtn").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    /*document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;*/
	$("html, body").animate({ scrollTop: 0 }, "slow");
}
function select(elem)
{
	id = elem.id;
	document.getElementById(id).class = "active";
}

function welcome(username) {
	
	if (localStorage.getItem("hasCodeRunBefore") === null || localStorage.getItem("hasCodeRunBefore") == "false") {
	   
		var x = document.getElementById("snackbar")
	    x.className = "show";
		x.innerHTML = "Welcome " + username + "!....";
	    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);

        localStorage.setItem("hasCodeRunBefore", true);
    }
	//cart operations
	var t = document.querySelector('[id$="status"]').id;
	var a = document.getElementById(t).value;
	var status = parseInt(a);
	//alert(status);
	var mssg;
	
	/*ERROR CODES
	 *  0 --> "item not found in database"
	 *  1 --> "demanded quantity not available"
	 *  2 --> "demanded quantity is available"
	 *  3 --> "user is not valid"
	 *  4 --> "cart is empty"
	 *  5 --> "order placed successfully"
	 *  6 --> "No orders placed till now"
	 *  7 --> "Please Login First"
	 */
	
	if(status == 1)
	{
		mssg = "Demanded quantity not available!...";
		if (localStorage.getItem("hasCodeRunBefore1") === null || localStorage.getItem("hasCodeRunBefore1") == "false")
		{
			var x = document.getElementById("snackbar")
		    x.className = "show";
			x.innerHTML = mssg;
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
			
			localStorage.setItem("hasCodeRunBefore1", true);
		}
		
	}
	else if(status == 2)
	{
		mssg = "Item successfully added to cart!...";
		if (localStorage.getItem("hasCodeRunBefore1") === null || localStorage.getItem("hasCodeRunBefore1") == "false")
		{
			var x = document.getElementById("snackbar")
		    x.className = "show";
			x.innerHTML = mssg;
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
			
			localStorage.setItem("hasCodeRunBefore1", true);
		}
		
	}
	else if(status == 3)
	{
		mssg = "Please Login from a valid account!...";
		if (localStorage.getItem("hasCodeRunBefore1") === null || localStorage.getItem("hasCodeRunBefore1") == "false")
		{
			var x = document.getElementById("snackbar")
		    x.className = "show";
			x.innerHTML = mssg;
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
			
			localStorage.setItem("hasCodeRunBefore1", true);
		}
		
	}
	else if(status == 4)
	{
		mssg = "Cart is empty!...";
		if (localStorage.getItem("hasCodeRunBefore1") === null || localStorage.getItem("hasCodeRunBefore1") == "false")
		{
			var x = document.getElementById("snackbar")
		    x.className = "show";
			x.innerHTML = mssg;
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
			
			localStorage.setItem("hasCodeRunBefore1", true);
		}
	}
	else if(status == 5)
	{
		
		if (localStorage.getItem("hasCodeRunBefore1") === null || localStorage.getItem("hasCodeRunBefore1") == "false")
		{
			var x = document.getElementById("snackbar");
		    x.className = "show";
			x.innerHTML = "Order placed successfully!";
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
			
			localStorage.setItem("hasCodeRunBefore1", true);
		}
	}
	else if(status == 6)
	{
		mssg = "No Orders placed till now!...";
		if (localStorage.getItem("hasCodeRunBefore1") === null || localStorage.getItem("hasCodeRunBefore1") == "false")
		{
			var x = document.getElementById("snackbar")
		    x.className = "show";
			x.innerHTML = mssg;
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
			
			localStorage.setItem("hasCodeRunBefore1", true);
		}
	}
	else if(status == 7)
	{
		mssg = "Please Login first!...";
		if (localStorage.getItem("hasCodeRunBefore1") === null || localStorage.getItem("hasCodeRunBefore1") == "false")
		{
			var x = document.getElementById("snackbar")
		    x.className = "show";
			x.innerHTML = mssg;
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
			
			localStorage.setItem("hasCodeRunBefore1", true);
		}
	}
	}


function opencart()
{
	localStorage.setItem("hasCodeRunBefore1", false);
}
function openorders()
{
	localStorage.setItem("hasCodeRunBefore1", false);
}
function search()
{
	localStorage.setItem("hasCodeRunBefore1", false);
}
function logout(username)
{
	localStorage.setItem("hasCodeRunBefore", false);
	
	var x = document.getElementById("snackbar");
    x.className = "show";
	x.innerHTML = "Bye " + username + "!....";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    
}
function login()
{
	localStorage.setItem("hasCodeRunBefore", false);
}
function remove(elem)
{
	var id = elem.id;
	document.getElementById('id01').style.display='block';
	var n = id[id.length - 1];
	id = 'c' + n;
	document.getElementById('here').innerHTML = "Remove "+ document.getElementById(id).innerHTML;
	var quan = document.getElementById('q'+n).innerHTML;
	var content = "";
	for(var i=1;i<=parseInt(quan);i++)
	{
		content += '<option value = "' + i + '">' + i + '</option>';
	}
	document.getElementById('drop').innerHTML = content;
	
	
	var h = document.getElementById('i'+n).innerHTML;
	var t = document.querySelector('[id$="here2"]').id;
	document.getElementById(t).value = h;
}
function shop()
{
	localStorage.setItem("hasCodeRunBefore1", true);
}
function updatequan()
{
	var e = document.getElementById('drop');
	var quan = e.options[e.selectedIndex].value;
	var t = document.querySelector('[id$="here1"]').id;
	document.getElementById(t).value = quan;
}
function addtocart()
{
	
	
	var id = elem.id;
	alert(quantitybought);
	
	
	//snackbar
	var x = document.getElementById("snackbar")
    x.className = "show";
	
	if(quantitybought == 0)
	{
		
		x.innerHTML = "Please Select Some Quantity !!...";
	    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
		//alert("Please select some quantity!!");
	}
	else
	{
		x.innerHTML = itemname + ' with quantity : ' + quantitybought + ' added to cart....';
	    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
	    
		//change the quantity available
	    var totalquaninstore = parseInt(e.options[e.options.length - 1].value);
	    var leftquantity = (totalquaninstore - quantitybought);
	    var drop = "";
	    for(var i=0;i<=leftquantity;i++)
	    {
	    	drop += '<option value="' + i + '">' + i + '</option>'
	    }
	    e.innerHTML = drop;
	    //update the number of items in navigation cart
	    var e = document.getElementById('cart');
	    var currquant = parseInt(e.innerText);
	    currquant++;
	    //alert(currquant);
	    e.innerHTML = '<a href="#about" id="cartq"><span class="fontawesome-shopping-cart"></span>' + currquant + '</a>';
	}	
}
function updatecart(elem)
{
	localStorage.setItem("hasCodeRunBefore1", false);
	idofbutton = elem.id;
	//fetch the item name 
	res = idofbutton.split("-");
	var n = res[1];
	document.getElementById('here').innerHTML = document.getElementById('desc-' + n).innerHTML;
	
	var name = document.getElementById('desc-' + n).innerHTML;
	
	var t = document.querySelector('[id$="here1"]').id;
	document.getElementById(t).value = name;
	//document.getElementById('j_idt17:here1').value = name;
	document.getElementById('id01').style.display='block';
	
}
function send()
{
	alert("send");
	sendtoserver([{name: 'name1', value: 'value1'}, {name: 'name2', value: 'value2'}]);
}

function redirect()
{
	window.location = "http://localhost:8080/MyEcommerce/items.xhtml";
}


