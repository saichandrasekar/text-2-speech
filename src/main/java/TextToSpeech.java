import java.io.IOException;

public final class TextToSpeech {
	
	private TextToSpeech() {
		
	}

	/**
	 * @param text
	 */
	public static void toSpeak(final String text) {
		try {
			Runtime.getRuntime()
					.exec("espeak -a 150 -p 85 -s 140 -g 5 -ven-us+f3 \"" + text.replaceAll(" ", "_") + "\"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
