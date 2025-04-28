$(document).ready(function () {


    $('#txtNIT', '#form').keypress(function (e) {
        var key = e.charCode || e.keyCode || 0;

        $nit = $(this);

        // Auto-format- do not expose the mask as the user begins to type
        if (key !== 8 && key !== 9) {

            if ($nit.val().length === 4) {
                $nit.val($nit.val() + '-');
            }

            if ($nit.val().length === 11) {
                $nit.val($nit.val() + '-');
            }

            if ($nit.val().length === 15) {
                $nit.val($nit.val() + '-');
            }

        }

        // Allow numeric (and tab, backspace, delete) keys only
        return (key === 8 ||
                key === 9 ||
                key === 46 ||
                (key >= 48 && key <= 57) ||
                (key >= 96 && key <= 105));

    }).bind('focus click', function () {
        $nit = $(this);

        if ($nit.val().length <= 17) {
            var val = $nit.val();
            $nit.val(val);
        }else{
            var val = $nit.val();
            $nit.val(val.substring(0,val.length - 1));
        }
        
    }).blur(function () {
        $nit = $(this);

        if ($nit.val() === '') {
            $nit.val('');
        }
        
        
        if($nit.val().length === 14){
            var str = $nit.val();
            var res = str.substring(0, 4);
            res += "-" + str.substring(4, 10);
            res += "-" + str.substring(10, 13);
            res += "-" + str.substring(13, 14);
            $nit.val(res);
        }
        
    });




});

