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
                    updateManagementTable();
                }else if(typeOfUser === 'PLAYER'){
                    $('#managerView').hide();
                    $('#startGame').show();


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

    $('#logout').click(function(e){
        e.preventDefault();

        $('#loginForm').show();
        $('#logoutForm, #managerView, #userWelcome, #startGame, #gameView').hide();

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

    $('#visibleWord').text(visibleWord);
    $('#status').text(status);
    $('#attemptsLeft').text(attemptsLeft);
    $('#availableCharacters').removeClass('alert alert-warning');
    $('#availableCharacters').empty();

    if(status === 'ACTIVE') {
        printOutAvailableCharacters(availableCharacters);
    }else{
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
    if(alreadyLoadedTable === false) {
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
                {"defaultContent" : "<button class='ui-icon-folder-open' id='open'>Open</button><button class='delete' id='delete'>Delete</button>"}



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
    alreadyLoadedTable = true;
}