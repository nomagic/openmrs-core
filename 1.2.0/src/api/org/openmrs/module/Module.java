package org.openmrs.module;

import java.io.File;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.Privilege;
import org.w3c.dom.Document;

/**
 * Generic module class that openmrs manipulates
 * @version 1.0
 */
public final class Module {

	private Log log = LogFactory.getLog(this.getClass());
	
	private String name;
	private String moduleId;
	private String packageName;
	private String description;
	private String author;
	private String version;
	
	private String updateURL;	// should be a URL to an update.rdf file
	private String updateVersion = null; // version obtained from the remote update.rdf file
	private String downloadURL = null; // will only be populated when the remote file is newer than the current module
	
	private Activator activator;
	private String activatorName;
	
	private String requireOpenmrsVersion;
	private String requireDatabaseVersion;
	private List<String> requiredModules;
	
	private List<AdvicePoint> advicePoints = new Vector<AdvicePoint>();
	
	private IdentityHashMap<String, String> extensionNames = new IdentityHashMap<String, String>();
	private List<Extension> extensions = new Vector<Extension>();
	
	private Map<String, Properties> messages = new HashMap<String, Properties>(); 
	private List<Privilege> privileges = new Vector<Privilege>();
	private List<GlobalProperty> globalProperties = new Vector<GlobalProperty>();
	private List<String> mappingFiles = new Vector<String>();
	
	private Document config = null;
	private Document sqldiff = null;
	
	// keep a reference to the file that we got this module from so we can delete
	// it if necessary
	private File file = null;
	
	private String startupErrorMessage = null;
	
	/**
	 * Simple constructor
	 * 
	 * @param name
	 */
	public Module(String name) {
		this.name = name;
	}
	
	/**
	 * Main constructor
	 * 
	 * @param name
	 * @param moduleId
	 * @param packageName
	 * @param author
	 * @param description
	 * @param version
	 */
	public Module(String name, String moduleId, String packageName, String author, String description, String version) {
		this.name = name;
		this.moduleId = moduleId;
		this.packageName = packageName;
		this.author = author;
		this.description = description;
		this.version = version;
		log.debug("Creating module " + name);
	}
	
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Module) {
			Module mod = (Module) obj;
			return getModuleId().equals(mod.getModuleId());
		}
		return false;
    }
	
	/**
	 * @return the activator
	 */
	public Activator getActivator() {
		try {
			ModuleClassLoader classLoader = ModuleFactory.getModuleClassLoader(this);
			if (classLoader == null)
				throw new ModuleException("The classloader is null", getModuleId());
			
			Class<?> c = classLoader.loadClass(getActivatorName());
			setActivator((Activator)c.newInstance());
		}
		catch (ClassNotFoundException e) {
			throw new ModuleException("Unable to load/find activator: '" + getActivatorName() + "'", name, e);
		}
		catch (IllegalAccessException e) {
			throw new ModuleException("Unable to load/access activator: '" + getActivatorName() + "'", name, e);
		}
		catch (InstantiationException e) {
			throw new ModuleException("Unable to load/instantiate activator: '" + getActivatorName() + "'", name, e);
		}
		
		return activator;
	}

	/**
	 * @param activator the activator to set
	 */
	public void setActivator(Activator activator) {
		this.activator = activator;
	}

	/**
	 * @return the activatorName
	 */
	public String getActivatorName() {
		return activatorName;
	}

	/**
	 * @param activatorName the activatorName to set
	 */
	public void setActivatorName(String activatorName) {
		this.activatorName = activatorName;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the requireDatabaseVersion
	 */
	public String getRequireDatabaseVersion() {
		return requireDatabaseVersion;
	}

	/**
	 * @param requireDatabaseVersion the requireDatabaseVersion to set
	 */
	public void setRequireDatabaseVersion(String requireDatabaseVersion) {
		this.requireDatabaseVersion = requireDatabaseVersion;
	}

	/**
	 * @return the requiredModules
	 */
	public List<String> getRequiredModules() {
		return requiredModules;
	}

	/**
	 * @param requireModules the requiredModules to set
	 */
	public void setRequiredModules(List<String> requiredModules) {
		this.requiredModules = requiredModules;
	}

	/**
	 * @return the requireOpenmrsVersion
	 */
	public String getRequireOpenmrsVersion() {
		return requireOpenmrsVersion;
	}

	/**
	 * @param requireOpenmrsVersion the requireOpenmrsVersion to set
	 */
	public void setRequireOpenmrsVersion(String requireOpenmrsVersion) {
		this.requireOpenmrsVersion = requireOpenmrsVersion;
	}

	/**
	 * @return the module id
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the module id to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the updateURL
	 */
	public String getUpdateURL() {
		return updateURL;
	}

	/**
	 * @param updateURL the updateURL to set
	 */
	public void setUpdateURL(String updateURL) {
		this.updateURL = updateURL;
	}

	/**
	 * @return the downloadURL
	 */
	public String getDownloadURL() {
		return downloadURL;
	}

	/**
	 * @param downloadURL the downloadURL to set
	 */
	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	/**
	 * @return the updateVersion
	 */
	public String getUpdateVersion() {
		return updateVersion;
	}

	/**
	 * @param updateVersion the updateVersion to set
	 */
	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}

	/**
	 * @return the extensions
	 */
	public List<Extension> getExtensions() {
		if (extensions.size() == extensionNames.size())
			return extensions;
		
		return expandExtensionNames();
	}

	/**
	 * @param extensions the extensions to set
	 */
	public void setExtensions(List<Extension> extensions) {
		this.extensions = extensions;
	}
	
	public void setExtensionNames(IdentityHashMap<String, String> map) {
		if (log.isDebugEnabled())
			for (Map.Entry<String, String> entry : extensionNames.entrySet()) {
				log.debug("Setting extension names: " + entry.getKey() + " : " + entry.getValue());
			}
		this.extensionNames = map;
	}
	
	private List<Extension> expandExtensionNames() {
		if (extensions.size() != extensionNames.size()) {
			for (Map.Entry<String, String> entry : extensionNames.entrySet()) {
				String point = entry.getKey();
				String className = entry.getValue();
				log.debug("expanding extension names: " + point + " : " + className);
				try {
					Class<?> cls = ModuleFactory.getModuleClassLoader(this).loadClass(className);
					Extension ext = (Extension)cls.newInstance();
					ext.setPointId(point);
					ext.setModuleId(this.getModuleId());
					extensions.add(ext);
					log.debug("Added extension: " + ext.getExtensionId() + " : " + ext.getClass());
				}
				catch (NoClassDefFoundError e) {
					log.warn("Unable to find class definition for extension: " + point, e);
				}
				catch (ClassNotFoundException e) {
					log.warn("Unable to load class for extension: " + point, e);
				}
				catch (IllegalAccessException e) {
					log.warn("Unable to load class for extension: " + point, e);
				}
				catch (InstantiationException e) {
					log.warn("Unable to load class for extension: " + point, e);
				}
			}
		}
		
		return extensions;
	}

	/**
	 * @return the advicePoints
	 */
	public List<AdvicePoint> getAdvicePoints() {
		return advicePoints;
	}

	/**
	 * @param advicePoints the advicePoints to set
	 */
	public void setAdvicePoints(List<AdvicePoint> advicePoints) {
		this.advicePoints = advicePoints;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Map<String, Properties> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, Properties> messages) {
		this.messages = messages;
	}

	public List<GlobalProperty> getGlobalProperties() {
		return globalProperties;
	}

	public void setGlobalProperties(List<GlobalProperty> globalProperties) {
		this.globalProperties = globalProperties;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Document getConfig() {
		return config;
	}

	public void setConfig(Document config) {
		this.config = config;
	}

	public Document getSqldiff() {
		return sqldiff;
	}

	public void setSqldiff(Document sqldiff) {
		this.sqldiff = sqldiff;
	}

	public List<String> getMappingFiles() {
		return mappingFiles;
	}
	
	public void setMappingFiles(List<String> mappingFiles) {
		this.mappingFiles = mappingFiles;
	}
	
	public boolean isStarted() {
		return ModuleFactory.isModuleStarted(this);
	}
	
	public void setStartupErrorMessage(String e) {
		if (e == null)
			throw new ModuleException("Startup error message cannot be null", this.getModuleId());

		this.startupErrorMessage = e;
	}
	
	public String getStartupErrorMessage() {
		return startupErrorMessage;
	}
	
	public Boolean hasStartupError() {
		return (this.startupErrorMessage != null);
	}
	
	public void clearStartupError() {
		this.startupErrorMessage = null;
	}
	
	public String toString() {
		if (moduleId == null)
			return super.toString();
		
		return moduleId;
	}

	
}
