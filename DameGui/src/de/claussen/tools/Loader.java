package de.claussen.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author Christian Ohlberg
 */
public class Loader {
	private String jarName;
	private File file = null;
	private HashMap<String, Class<?>> classes = new HashMap<String, Class<?>>();

	public Loader() {

	}

	private final String KI_TYPE = "ddd";

	/**
	 * @param fileName
	 *            The full path to the file.
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Loader(String fileName) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		this.loadFile(fileName);
	}

	/**
	 * @return Returns the current jar file.
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            A file object
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void loadFile(File file) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		this.file = file;
		if (!(file.getName().endsWith(".zip") || file.getName()
				.endsWith(".jar"))) {
			// Don't found a WronFileFormat exception.
			throw new FileNotFoundException("File must be a zip or jar");
		}
		this.jarName = file.getName().split("\\.")[0];
		loadAllClasses();
	}

	/**
	 * @param fileName
	 *            The full path to the file
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void loadFile(String fileName) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		this.loadFile(new File(fileName));
	}

	/**
	 * @return Gets the name of the jar.
	 */
	public String getJarName() {
		return jarName;
	}

	/**
	 * @param className
	 *            The name of the class to get from the jar.
	 * @return The class from the jar.
	 */
	public Class<?> getClassByName(String className) {
		if (this.classes.containsKey(className)) {
			return this.classes.get(className);
		}
		return null;
	}

	/**
	 * @param className
	 *            The name of the class to search for.
	 * @return A new instance of the class with the given class name
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public <T> T getClassByNameAsInstance(String className)
			throws InstantiationException, IllegalAccessException {
		if (this.classes.containsKey(className)) {
			return (T) this.classes.get(className).newInstance();
		}
		return null;
	}

	/**
	 * @param interfaceName
	 *            The name of the interface to search for.
	 * @return A new instance of the class with the given interface name.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public <T> T getClassAsInstanceWithInterface(String interfaceName)
			throws InstantiationException, IllegalAccessException {
		return (T) getClassWithInterface(interfaceName).newInstance();
	}

	/**
	 * @param interfaceName
	 *            The name of the interface to search for.
	 * @return The class with the given interface.
	 */
	public Class<?> getClassWithInterface(String interfaceName) {
		for (Class<?> c : this.classes.values()) {
			Type[] interfaces = c.getInterfaces();

			for (Type item : interfaces) {
				if (item.toString().contains(interfaceName)) {
					return c;
				}
			}
		}
		return null;
	}

	private void loadAllClasses() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		URLClassLoader loader = new URLClassLoader(new URL[] { file.toURI()
				.toURL() });

		JarInputStream jarStream = new JarInputStream(new FileInputStream(
				file.getAbsolutePath()));
		JarEntry jarEntry = jarStream.getNextJarEntry();

		while (jarEntry != null) {
			if (jarEntry.getName().endsWith(".class")) {
				String name = jarEntry.getName().replace(".class", "")
						.replace("/", ".");
				classes.put(name, loader.loadClass(name));
			}
			jarEntry = jarStream.getNextJarEntry();
		}
	}

	/**
	 * @Method Used to load a class from a JAR that implements the given
	 *         interface. Before using, set the path to the file with setFile.
	 * 
	 * @param path
	 *            The path to the jar that should hold the desired class.
	 * @param interfaceName
	 *            The name of the interface that the class have to implement.
	 * @return A new instance of the desired class.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T loadClassFrom(String path, String interfaceName)
			throws FileNotFoundException, IOException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		File file = new File(path);

		T result = null;
		if (file.exists()) {
			URLClassLoader loader = new URLClassLoader(new URL[] { file.toURI()
					.toURL() });

			JarInputStream jarStream = new JarInputStream(new FileInputStream(
					file.getAbsolutePath()));
			JarEntry jarEntry = jarStream.getNextJarEntry();

			while (jarEntry != null) {
				if (jarEntry.getName().endsWith(".class")) {
					String name = jarEntry.getName().replace(".class", "")
							.replace("/", ".");

					Class<?> c = loader.loadClass(name);
					Class<?> abstracts = c.getSuperclass();

					if (abstracts.getSimpleName().equals("Player")) {
						System.out.println("Klasse gefunden! "
								+ c.getSimpleName());
						result = (T) loader.loadClass(c.getName())
								.newInstance();
					}
				}

				jarEntry = jarStream.getNextJarEntry();
			}
		}

		return result;
	}
}
