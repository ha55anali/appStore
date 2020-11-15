# appStore

AppStore is a full fledged application that aims to mimic the functionality of popular mobile app stores like Google Play Store and iOS App Store. The app was written using almost entirely _Java_ with one of the two databases handled in _MS-SQL_.

### Project Contribution

- GUI Layer: Amaaz Ahmad (18L-0922)
- CLI Layer: Naveed Khan (18L-1216) and Hassan Ali (18L-0915)
- Business Layer: Nushirvan Naseer (18L-1233) and Hassan Ali (18L-0915)
- Text Database Layer: Afaq Ahmad (18L-1192)
- SQL Database Layer: Usama Naeem (18L-1203)

## Compilation Instructions
A bash script is used to launch the program. The script takes two arguments, the first argument takes text or sql while the second argument takes cli or gui. The front end is launched by starting the main program of the respective frontend; for the backend, the script overwrites the dbMode file(see backend for detail).
### Backend
The dbFactory is responsible for returning the objects of database. This was configured to read a config file(dbMode.txt) at the root of the project. If 1 is present in the file, the text database is used; otherwise sql backend is used.
### Frontend
The cli and gui have different main functions which launch their own front ends respectively.
