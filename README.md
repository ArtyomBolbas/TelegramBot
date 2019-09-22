# TelegramBot

  ##### Name - **cityInfoBot**
      
  ##### Bot name (username) - **@cityInfoSpring_bot** 
  
  ##### Token - **976278621:AAEZPQOUoSMLRvB517169FPex741wwcfuYs**
  
## Commands:
  
  ###### package command: _mvn clean package_
  ###### run project in the command line: _java -jar target/TelegaBot-0.0.1-SNAPSHOT.jar_
  
  1. Operation "Add:"	
   + localhost:8080/addCity
   + method: POST 
  ```
  {
    "city": "Minsk",
    "description": "It's a very wonderful city!"
  }
  ```
    
   2. Operation "Get info about the city:"
    + localhost:8080/getDisc?city=Minsk
    + method: GET
     
   3. Operation "Remove:"
    + localhost:8080/removeCity
    + method: DELETE
   ```
   {
     "city": "Minsk"
   }
   ``` 
   4. Operation "Update info about city:"
     + localhost:8080/updateDesc
     + method: PUT
   ```
   {
    "city": "Minsk",
     "description": "It's capital"
   }
   ``` 
   5. Operation "Remove the description of the city:"
     + localhost:8080/removeDesc
     + method: DELETE
   ```
   {
     "city": "Minsk"
   }
   ``` 
    
    properties:
      spring.jpa.hibernate.ddl-auto=update
      spring.datasource.url=jdbc:mysql://localhost:3306/telega?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
      spring.datasource.username=root
      spring.datasource.password=1111  (перед сборкой в пропертях указать СВОЙ пароль)
      spring.jpa.generate-ddl=true
