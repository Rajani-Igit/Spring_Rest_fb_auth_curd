var app = angular.module("mymodule", []);
app.config(['$httpProvider',
    function($httpProvider) {
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    }
]);
app.controller("myctrl", ["$scope", "$http", "$log", "customerService", "$timeout",
    function(scope, http, customerservice, log, $timeout) {
        var count = 1;
        scope.loadingshow = false;
        scope.attribute = "";
        scope.searchshow = false;
        scope.fname = "";
        scope.lname = "";
        scope.mobile = "";
        scope.password = "";
        scope.selectestate = "";
        scope.zip = "";
        scope.email = "";
        scope.showsuccessmessage = false;
        scope.show = false;
        scope.editshowhide = false;
        scope.genders = ["Male", "Female"];
        //scope.states = [ "Odisha", "Telengana", "AndhraPradesh", "Banglore" ];

        
        

        http.get("selectlist.do")
            .then(function(response) {
                scope.states = response.data;
            }, function(response) {
                alert("Content not loaded properly");
            });


        //By clicking Search button this function will call
        scope.search = function() {
            scope.searchattribute = "";
            scope.searchshow = true;
            scope.show = false;
            scope.totalusesrdirective = false;
            count = 1;
        }

        // By clicking addUser button this function will called
        scope.adduserdata = function() {
            count = 1;
            scope.fname = "";
            scope.lname = "";
            scope.mobile = "";
            scope.password = "";
            scope.selectestate = "";
            scope.zip = "";
            scope.email = "";
            scope.totalusesrdirective = false;
            scope.show = true;
            scope.searchshow = false;


        }



        //Submitting Search user this function will be called
        scope.searchuser = function(searchattribute) {
            scope.loadingshow = true;
            http.get('finduser.do', {
                params: {
                    email: searchattribute
                }
            }).then(function(success) {
                scope.show = true;
                console.log(success.data);
                scope.fname = success.data.fName;
                scope.lname = success.data.lName;
                scope.mobile = success.data.mobile;
                scope.selectestate = success.data.state;
                //scope.selectestate="Odisha";
                scope.selectedlang = success.data.gender;
                scope.zip = success.data.zip;
                scope.email = success.data.email;
                scope.show = true;
                scope.searchshow = false;
                scope.loadingshow = false;

            }, function(error) {
                scope.show = false;
                scope.showsuccessmessage = true;
                console.log(error);
                scope.successmessage = error.data.message;
                $timeout(function() {
                    scope.showsuccessmessage = false;
                }, 9000);
                console.log(error);
            });
        }


        //By clicking show alluser this function will be called
        scope.showallusers = function() {
            scope.loadingshow = true;
            scope.searchshow = false;
            alert("Allusers");

            http.get("getallusers.do", {
                params: {
                    pageno: count,
                    pageSize: 4
                }
            }).then(function(response) {
                scope.users = response.data;
                console.log(scope.users);
                console.log(response);
                scope.show = false;
                scope.editshowhide = false;
                scope.totalusesrdirective = true;
                $timeout(function() {
                    scope.loadingshow = false;
                }, 1000);

            }, function(error) {
                scope.showsuccessmessage = true;
                scope.successmessage = error.data.message;
                $timeout(function() {
                    scope.showsuccessmessage = false;
                }, 9000);
            });

        }

        //By clicking delete usesr this function will be called
        scope.remove = function(emailid) {
            var data = 'email=' + emailid;
            http.post('deleterecordbyemail.do', data)
                .then(function(success) {
                    scope.show = false;
                    scope.showsuccessmessage = true;
                    scope.successmessage = success.data;
                    scope.showallusers();
                    $timeout(function() {
                        scope.showsuccessmessage = false;
                    }, 9000);

                    console.log(success);

                }, function(error) {

                });
        }

        //By submiting addUser form this function will be called
        scope.submit = function() {
            scope.loadingshow = true;
            scope.searchshow = false;
            alert(scope.selectedlang);
            var data = {
                "email": scope.email,
                "fName": scope.fname,
                "gender": scope.selectedlang,
                "lName": scope.lname,
                "mobile": scope.mobile,
                "password": scope.password,
                "state": scope.selectestate,
                "zip": scope.zip,

            }

            http.post("add.do", data, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(function(success) {
                    scope.show = false;
                    scope.showsuccessmessage = true;
                    scope.successmessage = success.data.mesage;
                    scope.loadingshow = false;
                    $timeout(function() {
                        scope.showsuccessmessage = false;
                    }, 10000);
                    console.log(success);

                }, function(error) {
                    alert("inside error");
                    console.log(error);
                    scope.successmessage = error.data.message;
                    scope.showsuccessmessage = true;
                    $timeout(function() {
                        scope.showsuccessmessage = false;
                    }, 9000);
                });
        }

        scope.dummy = function(event) {
          /*  var data = {
                "email": "jajani769",
                "fName": "Rajani",
                "gender": "Male",
                "lName": "Pradhan",
                "mobile": "8895247580",
                "password": "1234",
                "state": "Odisha",
                "zip": "761126",

            }
            alert(" This is the dummy function For Testing");
            http({
                method: 'POST',
                url: 'dummy.do',
                params: {
                    fname: "Rajanikant"
                },
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function(response) {
                alert("Success")
            }, function() {
                alert("Eror occured")
            });*/
        }

        scope.fetchNextPage = function() {
            count++;
            alert(count);
            scope.showallusers();
        }

        scope.fetchPreviousPage = function() {
            if (count > 1) {
                count = count - 1;
            }
            scope.showallusers();
        }
        
        scope.excelGenerationFunc=function(){
        var excelData = scope.excelGenerationFunc1();
		var opts=scope.excelStylesFunc();
alasql('SELECT *  INTO  XLSXML("USER DETAILS.xls",?) FROM ?', [opts, excelData]);
        }  
   scope.excelGenerationFunc1=function(){
            var excelData = [];
              var len = scope.users.length;
              var ob1 = {
                 "0": "Report Name",
                 "1": "USER DETAILS",
                 "2": "",
                 "3": "User Name:",
                 "4": "",
                 "5": "",
                 "6": "",
                 "7": "",
                 "8": "",
                 "9": "",
                 "10": "",
                 "11": "",
                 "12": "",
                 "13": "",
                 "14": "",
                 "15": "",
                 "16": "",
                 "17": "",
                 "18": ""
             };
             var ob2 = {
                 "0": "",
                 "1": "",
                 "2": "",
                 "3": "",
                 "4": "",
                 "5": "",
                 "6": "",
                 "7": "",
                 "8": "",
                 "9": "",
                 "10": "",
                 "11": "",
                 "12": "",
                 "13": "",
                 "14": "",
                 "15": "",
                 "16": "",
                 "17": "",
                 "18": ""
             };
             excelData.push(ob1);
             excelData.push(ob2);
             var ob3 = {
                 "0": "Generated Time:"+new Date(),
                 "1": "",
                 "2": "",
                 "3": "",
                 "4": "",
                 "5": "",
                 "6": "",
                 "7": "",
                 "8": "",
                 "9": "",
                 "10": "",
                 "11": "",
                 "12": "",
                 "13": "",
                 "14": "",
                 "15": "",
                 "16": "",
                 "17": "",
                 "18": ""
             };
             var ob4 = {
                 "0": "",
                 "1": "",
                 "2": "",
                 "3": "",
                 "4": "",
                 "5": "",
                 "6": "",
                 "7": "",
                 "8": "",
                 "9": "",
                 "10": "",
                 "11": "",
                 "12": "",
                 "13": "",
                 "14": "",
                 "15": "",
                 "16": "",
                 "17": "",
                 "18": ""
             };
             excelData.push(ob3);
             excelData.push(ob4);
             var dObj = {
                 "0": "FIRST NAME",
                 "1": "LAST NAME",
                 "2": "GENDER",
                 "3": "STATE",
                 "4": "ZIP",
                 "5": "EMAIL ID",
                 "6": "MOBILE NO",
             };
             excelData.push(dObj);

             for (var i = 0; i < len; i++) {
                 var parentObj = scope.users[i];
               //  var rowLevel = jsonObj[i].level;
                 var excelObj = {
                     "0": parentObj.fName,
                     "1": parentObj.lName,
                     "2": parentObj.gender,
                     "3": parentObj.state,
                     "4": parentObj.zip,
                     "5": parentObj.email,
                     "6": parentObj.mobile,
                     
                 };
                 excelData.push(excelObj);
             }
     	 return excelData;
     	}

   scope.excelStylesFunc=function(){
             var opts = {
                 headers: false,
                 columns: [{
                     columnid: "0",
                     width: 150,
                     wraptext: true
                 }, {
                     columnid: "1",
                     width: 150,
                     wraptext: true
                 }, {
                     columnid: "2",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "3",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "4",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "5",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "6",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "7",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "8",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "9",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "10",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "11",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "12",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "13",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "14",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "15",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "16",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "17",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "18",
                     width: 90,
                     wraptext: true
                 }, {
                     columnid: "19",
                     width: 90,
                     wraptext: true
                 }, ],
                 column: {
                     style: {
                         Font: {
                             Bold: "1",
                             Color: "#3C3741",
                         },
                         Alignment: {
                             Horizontal: "Center"
                         },
                         Interior: {
                             Color: "#6699cc",
                             Pattern: "Solid"
                         }
                     },
                 },
                 rows: {
                     0: {
                         style: {
                             Font: {
                                 Bold: "1",
                                 Color: "#000099"
                             },
                             Interior: {
                                 Color: "#e6e6ff",
                                 Pattern: "Solid"
                             }
                         }
                     },
                     1: {
                         style: {
                             Font: {
                                 Bold: "1",
                                 Color: "#000099"
                             },
                             Interior: {
                                 Color: "#e6e6ff",
                                 Pattern: "Solid"
                             }
                         }
                     },
                     2: {
                         style: {
                             Font: {
                                 Bold: "1",
                                 Color: "#000099"
                             },
                             Interior: {
                                 Color: "#e6e6ff",
                                 Pattern: "Solid"
                             }
                         }
                     },
                     3: {
                         style: {
                             Font: {
                                 Bold: "1",
                                 Color: "#000099"
                             },
                             Interior: {
                                 Color: "#e6e6ff",
                                 Pattern: "Solid"
                             }
                         }
                     },
                     4: {
                         style: {
                             Font: {
                                 Bold: "1",
                                 Color: "#3C3741"
                             },
                             Interior: {
                                 Color: "#6699cc",
                                 Pattern: "Solid"
                             }
                         }
                     }
                 },

             };
       return opts
     }
        
        
        

    }
])

//Custom service  i have not implemented
app.service("customerService", ["$http",
    function(http) {
        this.saveCustomer = function(cb, lang, fname, lname, mobile, password, selectestate, email, zip) {
            // alert("first name" + fname);
            /*var emparry=[];
	var emp={};
	emp["name"]="Rajanikanta";
	emp["age"]=25;
	emp["organization"]="IBM";
	emp["salary"]=56000;
	
	var emp1={};
	
	emp1["name"]="SaiRam";
	emp1["age"]=25;
	emp1["organization"]="TCS";
	emp1["salary"]=36000;
	
var emp2={};
	
	emp2["name"]="Akshya Nalmwar";
	emp2["age"]=26;
	emp2["organization"]="Appstek";
	emp2["salary"]=6000;
	
	
var emp3={};
	
	emp3["name"]="Akshya Nalmwar";
	emp3["age"]=26;
	emp3["organization"]="Appstek";
	emp3["salary"]=6000;
	

	emparry.push(emp);
	emparry.push(emp1);
	emparry.push(emp2);
	emparry.push(emp3);
*/
            /*var req = {
			 method: 'GET',
			 url: 'addUser.htm',
			 params:{fname: fname}
			}

			$http(req).then(function(response){cb(response.data)}, function(){alert("Eror occured")});
	
	
	//return emparry;
*/
        }

    }
])




app.directive("formDirective", function() {

    return {
        templateUrl: "resources/assets/formDirective.html"

    }
})




app.directive("totalUserTable", function() {

    return {
        templateUrl: "resources/assets/totalUserTable.html",
        link: function(scope) {
            scope.clickFunc = function(email) {
                scope.remove(email);
            };
        }
    }
})


app.directive('myDirective', function (httpPostFactory) {
    return {
        restrict: 'A',
        scope: true,
        link: function (scope, element, attr) {

            element.bind('change', function () {
            	console.log(element[0].files[0]);
                var formData = new FormData();
                formData.append('file', element[0].files[0]);
                alert("Change");
              
                var file = element[0].files[0];
                var reader = new FileReader();
                reader.onloadend = function() {
                	 console.log("RESULT")
                 //console.log('RESULT', reader.result)
                  //$('#preview').attr('src',reader.result);
                }
                reader.readAsDataURL(file)
                httpPostFactory('dummy.do', formData, function (callback) {
                	var imagedata="data:image/jpeg;base64,"+callback.data;
                   $('#preview').attr('src',imagedata);
                	
                  console.log(imagedata);
                });
                
                
            });

        }
    };
});



app.factory('httpPostFactory', function ($http) {
    return function (file, data, callback) {
    	alert("data");
  
    	$http.post(file, data, {
            headers: {
                'Content-Type': undefined
            }
        })
            .then(function(success) {
            	callback(success);
            	//var imagedata="image/jpeg;base64,"+success.data;
           	// $('#preview').attr('src',imagedata);
              //  console.log(imagedata);

            }, function(error) {
                alert("inside error");
                //console.log(error);
              
            });
        
    };
});


