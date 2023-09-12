const rutFormater = (rut) => {
    return rut.toString().replace(/[^0-9,k,K]/g, '').toUpperCase(); // sustituye caracteres invalidos dentro de rut
}

const rutValidate = (rut) => {
    let digitos = rut.slice(0, rut.length - 1).split('').map(e => parseInt(e)); // digitos en un array
    let verificador = rut.slice(rut.length - 1, rut.length);
    let serie = [2, 3, 4, 5, 6, 7, 2, 3, 4, 5, 6, 7]
    let arr = [];
    let indexer = digitos.length - 1;
    for (let i = 0; i < digitos.length; i++) {
        arr.push(digitos[indexer] * serie[i]);
        indexer = indexer - 1;
    }
    let sum = arr.reduce((sum, value) => sum + value);
    let resto = sum - (11 * Math.trunc(sum / 11));
    let result = 11 - resto;
    if (result === 11) result = '0';
    else if (result === 10) result = 'k';

    return result.toString() === verificador;
}

const correoValidate = (correo) => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(correo).toLowerCase());
}

const headerFormater = (header) => {
    return header.toString().toLowerCase()
        .split(' ').join('') // quita espacios
        .normalize('NFD').replace(/[\u0300-\u036f]/g, ""); // quita acentos
}

function separarMiles(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

const numberValueVerify = (inputHolder) => {
    let number = inputHolder.value.toString().split('.').join('');
    let min = inputHolder.min;
    if (parseInt(number) < parseInt(min)) {
        inputHolder.value = min;
        Swal.fire("Valor menor al requerido", "el minimo es " + inputHolder.min, "warning");
        return false;
    }
    return true;
}

const numberValueMaxVerify = (inputHolder) => {
    let number = inputHolder.value.toString().split('.').join('');
    let max = inputHolder.max;
    if (parseInt(number) > parseInt(max)) {
        inputHolder.value = max;
        Swal.fire("Valor mayor al permitido", "el maximo es " + inputHolder.max, "warning");
        return false;
    }
    return true;
}

const dateConflictVerify = (fechaDesde, fechaHasta) => {
    if (fechaDesde !== '' && fechaHasta !== '') {
        let dateDesde = new Date(fechaDesde);
        let dateHasta = new Date(fechaHasta);
        if (dateHasta <= dateDesde) {
            Swal.fire("Fechas incompatibles", "", "warning");
            return false;
        }
        return true;
    }
}

const pageNumerator = (paginasTotales, paginaActual) => {
    let firstPage = 0;
    let lastPage = paginasTotales - 1;
    let enumerator = {};
    enumerator.numbers = Array.from(new Array(11), (x, i) => i + (paginaActual - Math.min(lastPage, 5))).filter((x) => x > -1 && x <= lastPage);
    enumerator.nearFirstPage = enumerator.numbers.includes(firstPage);
    enumerator.nearLastPage = enumerator.numbers.includes(lastPage);
    return enumerator;
}

const rutFormaterPermisivo = (rut) => {
    return rut.toString().replace(/[^0-9,k,K,\-,.]/g, '').toUpperCase(); // sustituye caracteres invalidos dentro de rut
}

const changePage = (event) => {
    let page = event.value;
    let params = new URLSearchParams(window.location.search);
    if (params.has('pageNumber')) {
        params.set('pageNumber', page);
    } else {
        params.append('pageNumber', page);
    }
    location.search = '?'.concat(params.toString());
}

const changeSort = (event) => {
    let sort = event.value;
    let params = new URLSearchParams(window.location.search);
    if (params.has('sortDirection')) {
        params.set('sortDirection', sort);
    } else {
        params.append('sortDirection', sort);
    }
    location.search = '?'.concat(params.toString());
}

function removeOptions(selectElement) {
    let i, L = selectElement.options.length - 1;
    for (i = L; i >= 0; i--) {
        selectElement.remove(i);
    }
}

const clearForm = () => {
    document.querySelectorAll('input').forEach((x) => {
        x.value = '';
    });
    document.querySelectorAll('select').forEach((x) => {
        x.value = null;
    });
}

const exportDocument = (ev) => {
    let id = ev.id;
    location.pathname = location.pathname.replace('listar', id);
    Swal.fire(
        {
            title: 'Generando reporte',
            html: 'Generando reporte solicitado.',
            allowOutsideClick: true,
            didOpen: () => {
                Swal.showLoading()
            }
        }
    );
}
const headerListHolder = () => {
    let holder = document.getElementById('header-list-holder');
    if (holder) {
        holder.innerHTML = '' + "<li class='nav-home'> <a href='#'><i class='flaticon-home'></i></a></li>";
        let paths = location.pathname.split('/').filter(e => e !== "");
        paths.forEach((path, index) => {
            let liSeparator = document.createElement('li');
            liSeparator.classList.add('separator');

            let i = document.createElement('i');
            i.classList.add('flaticon-right-arrow');

            liSeparator.appendChild(i);

            let liItem = document.createElement('li');
            liItem.classList.add('nav-item');
            let aItem = document.createElement('a');
            aItem.innerHTML = path;
            liItem.appendChild(aItem);

            holder.appendChild(liSeparator);
            holder.appendChild(liItem);
        });
    }
}
