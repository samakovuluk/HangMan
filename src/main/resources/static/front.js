$(document).ready( function () {

    alreadyLoadedTable = false;
    currentUserName = 'none';
    currentUserPassword= 'none';
    currentGame = '';
    currentGameId = 0;

    $('#login').click(function(e){
        e.preventDefault();

        currentUserName = $('#username').val();
        currentUserPassword = $('#password').val();

        $.ajax({
            url: '/api/login/',
            type: 'GET',
            dataType: 'text',
            timeout: 30000,

            success: function(data) {
                var typeOfUser = data;
                var userWelcome = 'Welcome ' + currentUserName
                $('#loginForm').hide();
                $('#userWelcome h3').text(userWelcome);
                $('#userWelcome, #logoutForm').show();
                if(typeOfUser === 'MANAGER'){
                    $('#managerView').show();
                    $('#wordView').show();
                    $('#addUser').show();
                    $('#addWord').show();
                    updateWordTable();
                    updateManagementTable();

                }else if(typeOfUser === 'PLAYER'){
                    $('#managerView').hide();
                    $('#startGame').show();
                    $('#openGames').show();




                }
            },
            error : function(xhr, ajaxOptions, thrownError) {

                alert('Invalid username or password. Please try again. thrownError:' + thrownError + 'xhr:' + xhr + 'ajaxOptions:'+ajaxOptions);

            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
            }
        });
    });

    $(document).on('click', '.characterToPlay', function () {
        var characterChosen = $(this).val();
        console.log(characterChosen.toString());
        console.log(characterChosen);
        $.ajax({
            url: '/api/game/' + currentGameId + '/',
            type: 'POST',
            data:  characterChosen.toString(),
            timeout: 30000,
            success: function(data) {
                currentGame = data;
                loadCurrentGame();
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
            }
        });
    });

    $('#startGame').click(function(e){
        e.preventDefault();
        $('#openGames').hide();
        $('#secretWord').hide();
        $('#gamesView').hide();
        $.ajax({
            url: '/api/game/start',
            type: 'GET',
            dataType: 'json',
            timeout: 30000,
            success: function(data) {
                currentGame = data;
                $('#gameView').show();
                loadCurrentGame();
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
            }
        });
    });
    $('#addU').click(function(e){
        e.preventDefault();
        var data="{ \"username\":\""+($('#usernameN').val())+
             "\", \"password\": \""+($('#passwordN').val())+
             "\", \"userType\": \"PLAYER\"}";


        $('#addUser').hide();
        $('#addUser').show();

console.log(data);
           $.ajax({
               url: '/api/users/',
               type: 'POST',
               data: data,
               dataType: 'json',
               contentType: 'application/json',
               timeout: 30000,
               success: function(data) {


                   updateManagementTable();
               },
               beforeSend: function (xhr) {
                   xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
               },
               error: function (data) {
                   alert("Error, cant add new user");


               }
           });



    });

    $('#addW').click(function(e){
        e.preventDefault();
        var data="{ \"word\":\""+($('#word').val())+"\"}";

        console.log(data);
        $.ajax({
            url: '/api/words/',
            type: 'POST',
            data: data,
            dataType: 'json',
            contentType: 'application/json',
            timeout: 30000,
            success: function(data) {
                updateWordTable();
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
            }
        });



    });
    $('#logout').click(function(e){
        e.preventDefault();
        $('#wordView').hide();
        $('#addWord').hide()
        $('#loginForm').show();
        $('#addUser').hide();
        $('#logoutForm, #managerView,#openGames,#gamesView, #userWelcome, #startGame, #gameView').hide();

    });
    $('#openGames').click(function(e){
        e.preventDefault();


        $('#gameView').hide();
        $('#gamesView').show();
        $("#gamesTable").dataTable().fnDestroy();
        var table =$('#gamesTable').DataTable({
            'ajax': {
                "type"   : "GET",
                "url"    : '/api/game',
                "dataSrc": "",
                "beforeSend": function (xhr) {
                    xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
                }
            },
            "columns": [
                {"data":  "id"},
                {"data": "secretWord"},
                {"data": "visibleWord"},
                {"data": "gameStatus"},
                {"data": "attemptsLeft"},
                {"data": "createDate"},
                {"data": "updateDate"}



            ]
        });



    });



});

function loadCurrentGame() {
    currentGameId = currentGame.id;
    var visibleWord = currentGame.visibleWord,
        attemptsLeft = currentGame.attemptsLeft,
        availableCharacters = currentGame.availableLetters,
        status = currentGame.gameStatus,
        secretWord = currentGame.secretWord;
    console.log(currentGame.availableLetters);
    console.log(secretWord)

    $('#visibleWord').text(visibleWord);
    $('#status').text(status);
    $('#attemptsLeft').text(attemptsLeft);
    $('#availableCharacters').removeClass('alert alert-warning');
    $('#availableCharacters').empty();

    if(status === 'ACTIVE') {
        printOutAvailableCharacters(availableCharacters);
    }else{
        $('#secretWord').show();
        $('#secretWord').text("The secret word was " + secretWord);

        $('#openGames').show();
        $('#availableCharacters').addClass('alert alert-warning');
        $('#availableCharacters').text("You cannot keep playing. Please, click on Start/Continue Game.");
        if(status === 'LOST') {
            $('#secretWord').show();
            $('#secretWord').text("The secret word was " + secretWord);
        }
    }
}

function printOutAvailableCharacters(availableCharacters) {
    $(availableCharacters).each(function () {
        var letter = $(this)[0],
            button = '<input class=\"characterToPlay\" type=\"button\" name=\"' + letter + '\" id=\"' + letter + '\" value=\"' + letter + '\">';
        $('#availableCharacters').append(button);
    });
}

function updateManagementTable(){

        $("#playersTable").dataTable().fnDestroy();
        var table =$('#playersTable').DataTable({
            'ajax': {
                "type"   : "GET",
                "url"    : '/api/player',
                "dataSrc": "",
                "beforeSend": function (xhr) {
                    xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
                }
            },
            "columns": [
                {"data": "id"},
                {"data" : "user.username"},
                {"data" : "wonGames"},
                {"data" : "failedGames"},
                {"data" : "lostGames"},
                {"defaultContent" : "<button class='delete' id='delete'>Delete</button>"}



            ]
        });
        $(document).on('click', '.delete', function () {
            var row = $(this).parent().parent();
            var data = table.row($(this).parents()).data();

             $.ajax({
                 url: '/api/users/'+data.userId,
                 type: 'DELETE',
                 timeout: 30000,
                 success: function(res) {
                    row.remove();

                 },
                 beforeSend: function (xhr) {
                     xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
                 }
             });
             console.log("success")


        });


}


function updateWordTable(){

    $("#wordsTable").dataTable().fnDestroy();
    var table =$('#wordsTable').DataTable({
        'ajax': {
            "type"   : "GET",
            "url"    : '/api/words',
            "dataSrc": "",
            "beforeSend": function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
            }
        },
        "columns": [
            {"data": "id"},
            {"data" : "word"},
            {"defaultContent" : "<button class='delete' id='deleteW'>Delete</button>"}



        ]
    });
    $(document).on('click', '.deleteW', function () {
        var row = $(this).parent().parent();
        var data = table.row($(this).parents()).data();

        $.ajax({
            url: '/api/words/'+data.id,
            type: 'DELETE',
            timeout: 30000,
            success: function(res) {
                row.remove();
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + btoa(currentUserName + ":" + currentUserPassword));
            }
        });
        console.log("success")


    });


}
