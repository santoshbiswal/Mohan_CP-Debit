<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>DTDC - DELIVERY PARTNER</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="../resource/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="../resource/css/cd_custom.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" media="all" href="../resource/css/daterangepicker.css" />
		<link href="../resource/css/datatables.min.css" rel="stylesheet" type="text/css" />
		<link href="../resource/fonts/css/all.css" rel="stylesheet">
		<script type="text/javascript" src="../resource/js/jquery-ui.js"></script>
		<script type="text/javascript" src="../resource/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="../resource/js/moment.js"></script>
		<script type="text/javascript" src="../resource/js/daterangepicker.js"></script>
		<script type="text/javascript" src="../resource/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="../resource/js/dataTables.bootstrap.min.js"></script>
		<script defer src="../resource/fonts/js/all.js"></script>
	</head>
	<body>
		<!----------------- Header Starts -------------------->
		<div class="header">
			<div class="dtdclogo">
				WELCOME TO <br/> <span>DTDC - DELIVERY PARTNER</span>
			</div>
			<div class="FRPLUS"></div>
		</div>
		<!----------------- Header Ends -------------------->


		<!----------------- Search Criteria Starts --------------------action="/CP-Debit/cpdebitmisresult" -->
		<div class="panel-default container-fluid" >
			<div class="panel-body" id="resultbody">
				<form id="cpdebitForm">
					<div class="form-group col-lg-2">
						<label>Shipment Number</label> <input class="form-control"
							type="text" name="shipNumber" id="shipNumber" />
						<%-- <input type="hidden" name="userId" value="${userId}"/> --%>
						
					</div>
					<div class="form-group col-lg-1" style="text-align: center;">
						<img src="../resource/images/or.png" alt="" />
					</div>
					<div class="form-group col-lg-2">
						<label>Select Booking  Period</label> <input class="form-control"
							type="text" name="daterange" onclick="forDateInput()"  id="daterange" placeholder="Enter Booking Period" />
					</div>
					<div class="form-group col-lg-1" style="text-align: center;">
						<img src="../resource/images/or.png" alt="" />
					</div>
					<div>
						<div  class="form-group col-lg-2">
							<label>Source</label> <!-- <input class="form-control"
								type="text" name="source" id="source" /> -->
								<select name="source" id="source" class="form-control">
										<option value="">select</option>
										<option value="Bangalore">Bangalore</option>
										<option value="Hyderabad">Hyderabad</option>
										<option value="Chennai">Chennai</option>
										<option value="Bhubaneswar">Bhubaneswar</option>
								</select>
						</div>
						<div class="form-group col-lg-2">
							<label>Destination</label> <!-- <input class="form-control"
								type="text" name="destination" id="destination" /> -->
								<select name="destination" id="destination"  class="form-control" >
									<option value="">select</option>
									<option value="Bangalore">Bangalore</option>
									<option value="Hyderabad">Hyderabad</option>
									<option value="Chennai">Chennai</option>
									<option value="Bhubaneswar">Bhubaneswar</option>
								</select>
						</div>
					</div>
					<div class="col-lg-2">
						<label>Process Status</label> <select title="Select status"
							class="form-control" name="status" id="status">
							<option>All</option>
							<option>pending</option>
							<option>delivered</option>
							<option>failure</option>
						</select>
					</div>
					<div class="form-group col-lg-2" style="margin-top: 20px;">
						<button type="submit"  id="submitButton" class="btn btn-success" >
							<span class="fa fa-check"></span>&nbsp;Submit
						</button>
					</div>
				</form>
			</div>
		</div>
	
		<!----------------- Search Criteria Ends -------------------->
		
		<!----------------- Search Result Table Starts -------------------->
		<div  class="panel-default container-fluid">
			<div id="table-div" class="panel-body">
				<table id="example" class="table table-striped table-bordered"></table>
			</div>
			<div id="message-div" class="panel-body"></div>
	 	</div>
	
		<!----------------- Search Result Table Ends -------------------->
	</body>
	<a href="/dtdc-logout.htm">Logout</a>	
	<script type="text/javascript">
		$(function() {
				$('input[name="daterange"]').daterangepicker({
					autoUpdateInput : false,
					locale : {
						cancelLabel : 'Clear'
					}
				});
				$('input[name="daterange"]').on(
						'apply.daterangepicker',
						function(ev, picker) {
							$(this).val(
									picker.startDate.format('DD/MM/YY') + ' - '
											+ picker.endDate.format('DD/MM/YY'));
				});
	
				$('input[name="daterange"]').on('cancel.daterangepicker',
					function(ev, picker) {
						$(this).val('');
				});
		});


		jQuery(document).ready(function($){

			$("#shipNumber").click(function(){

				$("#daterange").val("");
			    $("#source").val("");
			    $("#destination").val("");
			});

			$("#daterange").click(function(){
				
			   $("#shipNumber").val("");
			   $("#source").val("");
			   $("#destination").val("");
			});

			$("#source").change(function(){
				//enableSearchButton(false);
				  $("#daterange").val("");
				  $("#shipNumber").val("");

				  var source=$("#source").val();

				  var destination=$("#destination").val();

				  if(source==destination){
					alert("From source and destination city should not be same");
					$("#source").val(0);
					$("#destination").val(0) ;
					return;
				 }
			});

			$("#destination").change(function(){
				//enableSearchButton(false);
				  $("#daterange").val("");
				  $("#shipNumber").val("");
	
			      var source=$("#source").val();
	
				  var destination=$("#destination").val();
	
				  if(source==destination){
					alert("From source and destination city should not be same");
					$("#source").val(0);
					$("#destination").val(0) ;
					return;
				 }
		  	});

			$("#cpdebitForm").submit(function(event){

				event.preventDefault();

				var search={};

				search["shipNumber"]=$("#shipNumber").val();
				search["daterange"]=$("#daterange").val();
				search["source"]=$("#source").val();
				search["destination"]=$("#destination").val();
				search["status"]=$("#status").val();


				getShipmentBookingDetails(search);
					
			});

			function getShipmentBookingDetails(searchCriteria){

				var data=JSON.stringify(searchCriteria);
				//alert(data);

				 $.ajax({
			         type: "POST",
			         contentType: "application/json",
			         url: "/api/get-shipment-booking-details",
			         data: JSON.stringify(searchCriteria),
			         dataType: 'json',
			         cache: false,
			         timeout: 600000,
			         success: function (result) {

				         //alert("Success");
				         var stingResult=JSON.stringify(result);
				         console.log(stingResult);
				         //alert(stingResult);
				         displayTable(result);
			         },
			         error: function (e) {

			        	 alert("Failure");
			         }
			     });

			}

		});

		
		function displayTable(resultArr){


			if(resultArr.length==0){

				$("#message-div").html("No data found");
				$("#message-div").show();
				$("#table-div").hide();
			}


			$("#message-div").hide();
			var isSecondHit = document.getElementById("secondHit").value;
			if(secondHit != "Yes"){

				var tableHeaderRow = "<thead><tr><th>SL No</th><th>Shipment Number</th><th>Date of booking</th><th>Source</th><th>Destination</th><th>Status</th><th>Remarks</th></tr></thead>";
				var tableDataRow="<tbody>";

					for(var i = 0; i < resultArr.length; i++){

						var remarks = resultArr[i]["remarks"];
						if(remarks === null){

							remarks="";
						}

						tableDataRow=tableDataRow+"<tr><td>"+(i+1)+"</td><td><a href='/show-shipment-details.htm?shipmentId="+resultArr[i]["consignmentNumber"]+"'>"+resultArr[i]["consignmentNumber"]+"</td><td>"+resultArr[i]["dob"]+"</td><td>"+resultArr[i]["source"]+"</td><td>"+resultArr[i]["destination"]+"</td><td>"+resultArr[i]["deliveryStatus"]+"</td><td>"+remarks+"</td></tr>"
					}

					
					tableDataRow=tableDataRow+"</tbody>";

					var finalTable = tableHeaderRow+tableDataRow;

					$("#example").html(finalTable);
					$("#example").DataTable().search( '' ).columns().search( '' ).draw();
					$("#table-div").show();
				    document.getElementById("secondHit").value="Yes";
					
			}else{

				var dataTable = $('#example').DataTable();
				dataTable.clear();

				for(var i = 0; i < resultArr.length; i++){

					dataTable.row.add([ (i+1) ,
						  "<a href='/show-shipment-details.htm?shipmentId="+resultArr[i]["consignmentNumber"]+"'>"+resultArr[i]["consignmentNumber"]+"</a>",
						  resultArr[i]["dob"] ,
						  resultArr[i]["source"],
						  resultArr[i]["destination"], 
						  resultArr[i]["deliveryStatus"],
						  resultArr[i]["remarks"],
					]).search( '' ).columns().search( '' ).draw();
					$("#table-div").show();
				}

			}
			
		}

		window.onload = function() {


			 $('<input>').attr({
				    type: 'hidden',
				    id: 'secondHit',
				    name: 'bar',
				    value: 'No'
				}).appendTo('form');

			 $("#message-div").hide();
			 $("#table-div").hide();
		}			
	</script>
</html>