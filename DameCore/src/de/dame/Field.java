package de.dame;

public class Field {

	private int line;
	private char column;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + line;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Field))
			return false;
		Field other = (Field) obj;
		if (line != other.line)
			return false;
		if (getColumnLowerCase() != other.getColumnLowerCase())
			return false;
		return true;
	}

	public Field(int line, char column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public char getColumnUpperCase() {
		return Character.toUpperCase(column);
	}

	public char getColumnLowerCase() {
		return Character.toLowerCase(column);
	}

	public char getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return String.valueOf(line) + String.valueOf(column);
	}

}