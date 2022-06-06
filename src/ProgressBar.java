
public class ProgressBar {

	private StringBuilder progress;
	private String task;

	public ProgressBar(String task) {
		this.progress = new StringBuilder("\r[");
		this.task = task;
	}

	public void showProgress(int counter) {
		progress.delete(2, progress.length());
		for (int p = 0; p < counter; ++p) {
			progress.append('=');
		}
		progress.append('>');
		for (int p = counter; p < 20; ++p) {
			progress.append(' ');
		}
		progress.append("]   -   ").append(task);
		System.out.print(progress.toString());
	}

}
