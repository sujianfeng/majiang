/**
 * Created by sujianfeng on 2017/6/17.
 */
function isEmpty(strVal) {
    if (strVal == undefined || strVal == null || strVal == '') {
        return true;
    } else {
        return false;
    }
}
function getUnullValue(strVal) {
    if (strVal == undefined || strVal == null || strVal == '') {
        return "";
    } else {
        return strVal;
    }
}