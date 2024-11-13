/**
 * 
 */
 
     $(document).ready(function() {
        // 1) 텍스트 에디터 표시
        $('#summernote').summernote({
        placeholder: 'My Editor!',
        tabsize: 2,
        height: 200,
        toolbar: [
          ['style', ['style']],
          ['font', ['bold', 'clear']],
          ['color', ['color']],
          ['para', ['ul', 'ol']],
          ['table', ['table']],
          ['insert', ['picture']],
          ['view', ['fullscreen', 'codeview', 'help']]
        ]
      });

      
      $("btn-reset").click(()=> {
      // 제목 초기화 => input 요소의 value 속성을 초기화 (빈 값으로 설정)
        $("#title").val('');

      // 내용 초기화 => summernote 초기화. empty, inputStr, code, ...
        // $("#summernote").summernote('code', '');
        // $("#summernote").summernote('empty');
        // $("#summernote").summernote('insertText', '');
        $("#summernote").summernote('reset');
      });

      $("#btn-write").click(()=> {
        console.log("작성!");

        const title = $("#title").val();
        const content = $("#summernote").summernote('code');

        $.ajax({
          url: '/board',
          type: "POST",
          data: { 
              title: title,
              content: content
          },
          success: function(result) {
            console.log(result);
          },
          error: (err)=> {
            console.log(err);
          }
        });

      });

    });