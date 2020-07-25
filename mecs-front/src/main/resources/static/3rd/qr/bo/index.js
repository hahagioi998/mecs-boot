(function () {
  /**
   * ��ȡcookie
   * @param name
   * @returns {string}
   */
  var getCookie = function (name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) {
      return unescape(arr[2]);
    } else {
      return "";
    }
  };
  /**
   * ��ȡָ���汾
   * @param version_name
   * @returns {string}
   */
  var abtp = function (version_name) {
    //��ȡ cookie�е����� ab�������� a1_1.c1_1
    var abtp_list_str = getCookie('claf');
    //��ֵ,�����ݴ���� { "a1": "1", "c1": "1" }
    var result = {};
    if (abtp_list_str) {
      var arr = abtp_list_str.split('.');
      for (var i = 0, len = arr.length; i < len; i++) {
        var keyValueArr = arr[i].split('_');
        var key = keyValueArr[0] || '';
        var value = keyValueArr[1] || '';
        result[key] = value;
      }
    }
    return version_name ? result[version_name] : result;
  };
  window.abtp = abtp;
})();