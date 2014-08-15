var parentId = "action";

function Menu(data){
	var buf = new StringBuffer();
	buf.append('<div class="accordion" id="').append(parentId).append('">');
	var i=0;
	$(data).each(function(){
		
		buf.append('<div class="accordion-group"><div class="accordion-heading">')
		.append('<a class="accordion-toggle" data-toggle="collapse" data-parent="#')
		.append(parentId)
		.append('" href="#').append(this.module.id).append('">').append(this.module.name)
		.append('</a></div>')
		.append('<div id="').append(this.module.id);
		if(i==0){
			buf.append('" class="accordion-body in collapse"><ul class="nav nav-list ">'); //默认打开
		}else{
			buf.append('" class="accordion-body  collapse"><ul class="nav nav-list ">');
		}
		$(this.menus).each(function(){
			if(i==0){
				buf.append('<li class="active"><a href="#">调度管理</a></li>');
			}else{
				buf.append('<li><a href="#">').append(this.name).append('</a></li>');
				
			}
			i++;
		});
		i++;
		buf.append('</ul></div></div>');
	});
	buf.append("</div>");
	
	this.toString = function(){
		return buf.toString();
	};
}
var DEMO = [
		      {
		    	  module:{
		    		  id:'1'
		    		  ,name : '基本设置'	  
		    	  }
		      	 ,menus:[{
		      		 name:'用户管理'
		      	 		},{
		      	 	 name :'角色管理'
		      	 		}
		      	 		
		      	 		]
		      },
		      {
		    	  module:{
		    		  id:'2'
		    		  ,name : '系统设置'	  
		    	  }
		      	 ,menus:[{
		      		 name:'告警管理'
		      	 		},{
		      	 	 name :'调度管理'
		      	 		}
		      	 		
		      	 		]
		      }
		      
		      ];



	
	




	
	
	
         

			
             
         
	


