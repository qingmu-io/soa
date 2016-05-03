package io.goudai.soa.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
        static {
                try {
                        Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
        }

        public static String getCreateTablesSQL(String table) {
                try {
                        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soa?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull", "root", "");
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("show create table " + table);
                        rs.next();
                        String sql = rs.getString(2);
                        rs.close();
                        st.close();
                        connection.close();
                        return sql;
                } catch (SQLException e) {
                        throw new RuntimeException("数据库发生异常",e);
                }
        }
        public static void main(String[] args) {
        	 String sql = getCreateTablesSQL("sys_user");
        	 String [] cols = 
        	sql.substring(sql.indexOf("(")+1, sql.lastIndexOf("NULL")+4).split(",");
        	 sql = "";
        	 String filed = null;
        	 String map = "";
        	 for(String col : cols){
        		col = col.substring(col.indexOf("`")+1, col.lastIndexOf("`"));
        		if(col.contains("_")){
        			String[] split = col.split("_");
        			filed=split[0];
					for(int i=1;i<split.length;i++){
        				filed+=split[i].substring(0,1).toUpperCase()+split[i].substring(1,split[i].length());
        			}
        		}else{
        			filed = col;
        		}
        		sql = sql+col +" AS "+filed +"\n";
        		map+="context.addAttr("+filed+",)";
        	 }
        	 //生成查询的隐射
        	 System.out.println(sql);
        	 System.out.println(map);
        	 
        	 
        	 
        	 
		}
        
}
