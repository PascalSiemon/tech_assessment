## Tech Assessment


# Backend Application
Make sure that Java is installed and properly configured on your machine.
Run the application as follows:
- Navigate console to directory 'backend' of the project
- Run the application by executing 'mvn spring-boot:run'
- The application should start up and eventually be available on port 8080

# Frontend Application
Make sure that you have all the prerequisites installed to run a simple Angular application.
Then, do the following:
- Navigate console to directory 'frontend' of the project
- Execute 'npm install' to install dependencies
- Execute 'ng serve' to start the application
- If you started the backend application first, you should see the application now running on port 4200
 
# Improvements
- Remove hardcoded customer details in backend (and move them appropriately to the frontend)
- Secure connection with certicates
- Create Docker containers for the applications
- Improve visualization in frontend and refactor components (e.g. a bank account could be an own rather generic component for easier inclusion of different banks)
- Implement requirement #2 about transactions
- With requirement #2: Add routing and lazy loading for new pages
- If more data shall be processed and changes are possible: Introduce state management in order to handle data throughout the Angular application
- Introduce unit tests
- Implement data extraction from other banks of the same customer
- Add Exception handling (currently the backend would probably crash, if NatWest is not available