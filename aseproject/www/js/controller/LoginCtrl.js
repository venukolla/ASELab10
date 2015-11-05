/**
 * Created by Venu on 10/11/15.
 */

myapp.controller('LoginCtrl',function($scope ,$http, $state,$cordovaNetwork){




  $scope.login = function(data){

    var urlTestString = URLRetriever.getInstance();

    console.log("hiii")

    console.log(urlTestString.urlString())

    console.log(data)

   // var isOnline = $cordovaNetwork.isOnline()

   // alert(isOnline)
    var req = {
      method: 'POST',
      url: 'http://mongorestserviceapitest.mybluemix.net/api/mongo/login',
      data: data
    };

    $http(req).then(function(response) {
      console.log(response+"response received from console");

      var status = response.data.statusmessage;

      //$cordovaSms.send('8164622782',status).then(function(){
      //  console.log("message success")
      //})

      if(status == 'success'){
        console.log("in success state")
       // alert("hello")

        if(data.usertype =='admin'){
          $state.go('adminhome');
        }else{
          $state.go('homepage.coursespage')
        }


      }else{
        console.log("in failure state")
        $state.go('login')
      }


    }, function(data) {
      console.log(data);
    });

  };


});

