/* [ ---- Gebo Admin Panel - tables ---- ] */

	$(document).ready(function() {
		//* datatable must be rendered first
        gebo_galery_table.init();
        //* actions for tables, datatables
        //gebo_select_row.init();
		//gebo_delete_rows.simple();
		//gebo_delete_rows.dt();
		//$('#dt_gal').dataTable();
	});

	//* select all rows
	gebo_select_row = {
		init: function() {
			$('.select_rows').click(function () {
				var tableid = $(this).data('tableid');
                $('#'+tableid).find('input[name=row_sel]').attr('checked', this.checked);
			});
		}
	};
	
	//* delete rows
	gebo_delete_rows = {
		simple: function() {
			$(".delete_rows_simple").on('click',function (e) {
				e.preventDefault();
				var tableid = $(this).data('tableid');
                if($('input[name=row_sel]:checked', '#'+tableid).length) {
                    $.colorbox({
                        initialHeight: '0',
                        initialWidth: '0',
                        href: "#confirm_dialog",
                        inline: true,
                        opacity: '0.3',
                        onComplete: function(){
                            $('.confirm_yes').click(function(e){
                                e.preventDefault();
                                $('input[name=row_sel]:checked', '#'+tableid).closest('tr').fadeTo(300, 0, function () { 
                                    $(this).remove();
                                    $('.select_rows','#'+tableid).attr('checked',false);
                                });
                                $.colorbox.close();
                            });
                            $('.confirm_no').click(function(e){
                                e.preventDefault();
                                $.colorbox.close(); 
                            });
                        }
                    });
                }
			});
		},
        dt: function() {
			$(".delete_rows_dt").on('click',function (e) {
				e.preventDefault();
				var tableid = $(this).data('tableid'),
                    oTable = $('#'+tableid).dataTable();
                if($('input[name=row_sel]:checked', '#'+tableid).length) {
                    $.colorbox({
                        initialHeight: '0',
                        initialWidth: '0',
                        href: "#confirm_dialog",
                        inline: true,
                        opacity: '0.3',
                        onComplete: function(){
                            $('.confirm_yes').click(function(e){
                                e.preventDefault();
                                $('input[name=row_sel]:checked', oTable.fnGetNodes()).closest('tr').fadeTo(300, 0, function () {
                                    $(this).remove();
									oTable.fnDeleteRow( this );
                                    $('.select_rows','#'+tableid).attr('checked',false);
                                });
                                $.colorbox.close();
                            });
                            $('.confirm_no').click(function(e){
                                e.preventDefault();
                                $.colorbox.close(); 
                            });
                        }
                    });
                }    
			});
		}
	};
	
    //* gallery table view
    gebo_galery_table = {
        init: function() {
			$('#dt_gal').dataTable({
				"sDom": "<'row'<'span6'<'dt_actions'>l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
				"sPaginationType": "full_numbers",
                "aaSorting": [[ 2, "asc" ]],
				"aoColumns": [
					{ "bSortable": false },
					{ "bSortable": false },
					{ "sType": "string" },
					{ "sType": "formatted-num" },
					{ "sType": "eu_date" },
					{ "bSortable": false }
				],
				"oLanguage": {
	                "sLengthMenu": "每页显示 _MENU_条",
	                "sZeroRecords": "没有找到符合条件的数据",
	                "sProcessing": "<img src=’./loading.gif’ />",
	                "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
	                "sInfoEmpty": "没有记录",
	                "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
	                "sSearch": "搜索：",
                	"oPaginate": {
    	            	"sFirst": "首页",
    	            	"sPrevious": "前一页",
    	            	"sNext": "后一页",
    	            	"sLast": "尾页"
    		        }
				}
			});
           $('.dt_actions').html($('.dt_gal_actions').html());
        }
    };