# TelegramBot

      name - cityInfoBot
      
      Bot name (username) - @cityInfoSpring_bot 
      token - 976278621:AAEZPQOUoSMLRvB517169FPex741wwcfuYs
  
      package command: mvn clean package
      run project in the command line: java -jar target/TelegaBot-0.0.1-SNAPSHOT.jar
  
     1. oper add:	
     localhost:8080/addCity
     method: POST 
     ```
     {
       "city": "Minsk",
       "description": "It's a very wonderful city!"
     }
     ```
    
     2. oper get info about the city:
     - getDisc;
     localhost:8080/getDisc?city=Minsk
     method: GET
     
     3. oper remove:
     - removeCity;
     localhost:8080/removeCity
     method: DELETE
     {
       "city": "Minsk"
     }
      
     4. oper update info about city:
     - updateDesc;
     localhost:8080/updateDesc
     method: PUT
     {
       "city": "Minsk",
       "description": "It's capital"
     }
      
     5.   oper remove the description of the city:
     - removeDesc;
     localhost:8080/removeDesc
     method: DELETE
     {
       "city": "Minsk"
     }
    
    properties:
      spring.jpa.hibernate.ddl-auto=update
      spring.datasource.url=jdbc:mysql://localhost:3306/telega?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
      spring.datasource.username=root
      spring.datasource.password=1111  (перед сборкой в пропертях указать СВОЙ пароль)
      spring.jpa.generate-ddl=true
