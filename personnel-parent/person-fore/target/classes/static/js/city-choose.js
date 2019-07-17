$(function () {

    'use strict';


    var $citypicker1 = $('#city-picker1');

    $citypicker1.citypicker();

    var $citypicker2 = $('#city-picker2');

    $citypicker2.citypicker({
        province: '湖南省',
        city: '长沙市',
        district: '天心区'
    });

    var $citypicker3 = $('#city-picker3');

    $('#reset').click(function () {
        $citypicker3.citypicker('reset');
       
        
    });
    $('#reset2').click(function () {
        $citypicker2.citypicker('reset');
       
        
    });
    

    $('#destroy').click(function () {
        $citypicker3.citypicker('destroy');
    });
    

});
