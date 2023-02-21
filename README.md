     _                           __  __           _ _       
    | |    ___ _ __ ___  _   _  |  \/  | ___ _ __| (_)_ __  
    | |   / _ \ '__/ _ \| | | | | |\/| |/ _ \ '__| | | '_ \ 
    | |__|  __/ | | (_) | |_| | | |  | |  __/ |  | | | | | |
    |_____\___|_|  \___/ \__, | |_|  |_|\___|_|  |_|_|_| |_|
                         |___/                              
    
# How to test the application

    curl -w '\n' 'http://localhost:8080/api/v1/lockers/reserveLocker?year=2023&month=11&day=4&length=14&width=15&height=16&orderId=TMM-123'

    -> {"lockerCode":"ME-5"}