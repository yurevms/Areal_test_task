document.addEventListener("DOMContentLoaded", function() {

    // ===== Маска для паспорта: 4 цифры + пробел + 6 цифр =====
    const passportInputs = document.querySelectorAll('input[name="passportNumber"]');
    passportInputs.forEach(input => {
        input.addEventListener("input", function() {
            let val = input.value.replace(/\D/g, ''); // оставляем только цифры
            if (val.length > 4) {
                val = val.slice(0,4) + ' ' + val.slice(4,10);
            }
            input.value = val;
        });
    });

    // ===== Маска для телефона: +7 (999) 999-99-99 =====
    const phoneInputs = document.querySelectorAll('input[name="contactInfo"]');
    phoneInputs.forEach(input => {
        input.addEventListener("input", function() {
            let val = input.value.replace(/\D/g, ''); // оставляем только цифры
            if (val.startsWith("7")) val = val.slice(1); // удаляем ведущую 7, если есть
            val = val.slice(0,10); // максимум 10 цифр после кода страны
            let formatted = "+7 ";
            if(val.length>0) formatted += "(" + val.substring(0,3);
            if(val.length>=4) formatted += ") " + val.substring(3,6);
            if(val.length>=7) formatted += "-" + val.substring(6,8);
            if(val.length>=9) formatted += "-" + val.substring(8,10);
            input.value = formatted;
        });
    });

    // ===== Проверка на корректность перед отправкой формы =====
    document.querySelectorAll("form").forEach(form => {
        form.addEventListener("submit", function(e) {
            const passport = form.querySelector('input[name="passportNumber"]').value;
            const phone = form.querySelector('input[name="contactInfo"]').value;

            const passportPattern = /^\d{4} \d{6}$/;  // теперь 4 цифры + пробел + 6 цифр
            const phonePattern = /^\+7 \(\d{3}\) \d{3}-\d{2}-\d{2}$/;

            if (!passportPattern.test(passport)) {
                alert("Неверный паспорт! Формат: 1234 567890");
                e.preventDefault();
            } else if (!phonePattern.test(phone)) {
                alert("Неверный телефон! Формат: +7 (999) 999-99-99");
                e.preventDefault();
            }
        });
    });

});
