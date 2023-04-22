package com.codetreatise.view;

import java.util.ResourceBundle;

public enum FxmlView {

    USER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/User.fxml";
        }
    }, 
    MAINWORKER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mainWorker.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Main_Worker.fxml";
        }
    }, 
    MAINADMIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mainAdmin.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Main_Admin.fxml";
        }
    }, 
    MAINOWNER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mainOwner.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Main_Owner.fxml";
        }
    }, 
    LOGIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },
    HIRE {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("hire.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Hire.fxml";
        }
    },
    ABOUT {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("about.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/About.fxml";
        }
    }, 
    RENT{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("rent.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Rent.fxml";
        }
    	
    },
    RETURN{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("return.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Return.fxml";
        }
    	
    },
    BOOK{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("book.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Book.fxml";
        }
    },
    LIBRARY{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("library.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Librariers.fxml";
        }
    },
    READER{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("reader.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Reader.fxml";
        }
    },
    PLANADMIN{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("planAdmin.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/PlanAdmin.fxml";
        }
    },
    PLANUSER{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("planUser.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/PlanUser.fxml";
        }
    },
    PLANOWNER{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("planOwner.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/PlanOwner.fxml";
        }
    },
	COPYOFBOOK{
    	@Override
		public String getTitle() {
            return getStringFromResourceBundle("copyOfBook.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/CopyOfBook.fxml";
        }
    	
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
