dict = {};
dict["1"] = "folders";
cnt = 1;

function getFolders(id) {
    let elem = $('#' + id);
    if (elem.hasClass('opened') === true) {
        return;
    }
    elem.addClass('opened');

    let newListElem;
    let path = dict[id];
    let $ul = elem.children(":first"); // first child element with #id

    let request = new XMLHttpRequest();
    request.onload = function () {
        if (request.readyState === 4 && request.status === 200) {
            const folders = JSON.parse(this.responseText);
            if (folders.length === 2) {
                const $li_nou = document.createElement('li');
                $ul.append($li_nou);
            }
            for (let i = 2; i < folders.length; i++) {
                cnt++;
                let folder = folders[i];
                if (folders[i].indexOf('.') === -1) {
                    newListElem = document.createElement('li');
                    newListElem.innerText = folder;
                    const new_ul = document.createElement('ul');
                    dict[cnt] = path + '/' + folder;
                    let val = cnt;
                    $(newListElem).on('click', function () {
                        getFolders(val);
                    });
                    $(newListElem).attr('id', cnt);
                    $(newListElem).append(new_ul);
                    $(newListElem).css("color", "blue");
                    $ul.append(newListElem);
                } else {
                    newListElem = document.createElement('li');
                    newListElem.innerText = folder;
                    dict[cnt] = path + '/' + folder;
                    let val = cnt;
                    $(newListElem).on('click', function () {
                        getFile(val);
                    });
                    $ul.append(newListElem);
                }
            }
        }
    };
    
    request.open("GET", "http://localhost/pb5_getFolders.php?path=" + path, true);
    request.send();
}

function getFile(id) {
    let path = dict[id];
    let request = new XMLHttpRequest();
    request.onload = function () {
        if (request.readyState === 4 && request.status === 200) {
            const content = this.responseText;
            $('#explorer').val(content);
        } else {
            alert('Eroare request.status getfiles' + request.status);
        }
    };
    request.open("GET", "http://localhost/pb5_getFiles.php?path=" + path, true);
    request.send();
}