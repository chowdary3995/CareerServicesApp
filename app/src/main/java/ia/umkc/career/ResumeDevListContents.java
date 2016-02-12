/**
 * 
 */
package ia.umkc.career;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author rr5h4
 * 
 */
public class ResumeDevListContents extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		int rowIndex = getIntent().getExtras().getInt("rowIndex");
		int rowPointer = getIntent().getExtras().getInt("rowPointer");
		if (rowPointer == 0) {
			getResumeView(rowIndex);
		} else if (rowPointer == 1) {
			getCoverLetterView(rowIndex);
		} else if (rowPointer == 2) {
			getInterviewingView(rowIndex);
		}
	}

	private void getInterviewingView(int rowIndex) {
		WebView webview;
		String rowElement = "";
		webview = new WebView(this);
		webview.setBackgroundResource(R.drawable.white);
		webview.setBackgroundColor(0);
		setContentView(webview);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		TextView title= (TextView) findViewById(R.id.myTitle);
		switch (rowIndex) {
		case 0:
			// webview = new WebView(this);
			// setContentView(webview);
			rowElement = "<html><body><ul><li>Assess your strengths and talents as it relates to the position &#45;Research the position and research the company</li><br/><li>Conduct a mock interview</li></ul></body></html>";
			title.setText("Steps in Interview Process");
			webview.loadData(rowElement, "text/html", null);
			// webview.loadData(rowElement, "text/html", null);
			break;
		case 1:
			rowElement = "<html><body><ol><li>Tell me about yourself. <ol type = a><li>Tip&#45; Focus on sharing relevant information such as your experiences, skills, and achievements that will be important to the employer </li> </ol></li><br/><li>What are some of your strengths? </li><br/><li>What is a weakness? </li><br/><li>How will you make a contribution to our company/organization? </li><br/><li>If I asked your last boss to describe you, what would he/she say? </li><br/><li>Tell me about a time when you were in a leadership position/role. <ol type = \"a\"><li>Tip&#45; Use specific examples to support your answers and avoid talking negatively about previous employers or supervisors </li> </ol></li><br/><li>How do you define success? </li><br/><li>What do you consider to be your greatest accomplishment thus far? </li><br/><li>Tell me about a time when you have encountered a conflict with your boss/co&#45;worker and how you handled it? </li><br/><li>How do you work under pressure? </li><br/><li>Where do you see yourself in the next 5&#45;10 years? </li><br/><li>Do you have any questions for me? <ol type = \"a\"> <li >Tip&#45; Ask 2&#45;3 questions to find out information not posted on the company&#39;s website </li><br/><li>	Examples <ol type = \"i\"> <li>What is an average day like for someone in this position? </li><br/><li> What are the three top goals for the position for the coming year? </li><br/><li> What is the next step in your candidate selection process? </li></ol> </li></ol></li> </ol></body></html>";
			title.setText("Common Questions");
			webview.loadData(rowElement, "text/html", null);
			break;
		case 2:
			rowElement = "<html><body><ul><li>Write a thank you card or letter to confirm your interest and to inquire about the next step in their candidate selection process. Send the card or letter 24&#45;48 hours after the interview to everyone you spoke with during the interview. This will distinguish you from the other candidates. </li><br/><li>Consider each interview a learning process, whether you are offered the job or not. Take thorough notes of what you did well and what you could improve for the next interview. </li><br/><li><b>If you are offered the position, ask the employer for time to make your decision. </b> Employers will work with you to set a deadline for when you must communicate your decision. </li><br/><li> <b>If you are not offered the position, you may professionally ask the employer how you may improve for your candidacy and interview style for future opportunities. </b> </li></ul></body></html>";
			title.setText("Following up");
			webview.loadData(rowElement, "text/html", null);
			break;
		case 3:
			rowElement = "<html><body><table><tr><td><h4><a href =\"#men\">Men</a></h4></td><td>&nbsp;&nbsp;&nbsp;</td><td><h4><a href=\"#women\">Women</a></h4></td></tr></table><h4><a name=\"men\">Men</a></h4><ul><li>Traditional business attire is a must. Wear a conservative two&#45;piece business suit (dark navy, charcoal or black). </li><br/><li>Conservative long&#45;sleeved shirt/blouse (white is best, solid blue is an alternative). </li><br/><li>Polished shoes (black or dark brown lace up or tasseled loafers are best). Never wear tennis or sport shoes. Stay away from white or tan shoes. </li><br/><li>Wear dark socks that coordinate with the outfit; Never wear white socks! </li><br/><li>Necktie should be silk with conservative pattern. Make sure the knot is neat and the tie comes to the top of your belt or slacks. Be sure the tie is wrinkle free. </li><br/><li>Wear a dark belt preferable black and conservative style with a clean/functional belt buckle. </li><br/><li>Short haircuts are best; facial hair is okay only if it is well&#45;groomed. </li><br/><li>Clean, trimmed fingernails are a must. </li></ul><h4><a name=\"women\">Women</a></h4><ul><li>Always wear a conservative suit with a jacket (dark navy, charcoal or black); No dresses! </li><br/><li>Know your industry. Fashion, advertising and the arts allow for more creativity than more traditional careers such as finance, law and accounting. </li><br/><li>No high heels; Closed toes and closed heels are best. Keep shoes clean and polished. Avoid tennis shoes or sandals. </li><br/><li>Conservative hosiery at or near skin color </li><br/><li>No purses, small or large; carry a briefcase instead. </li><br/><li>Use a clear or conservative nail polish if worn </li><br/><li>Minimal use of makeup &#45; it should enhance your appearance&#46;&#46;&#46; not take away from it. </li><br/><li>Jewelry: Avoid wearing any clothing or accessories that might distract the interviewer. Wear only 3&#45;4 pieces of jewelry (avoid dangling earrings). </li><br/><li>Well&#45;groomed hairstyle. Hair should be freshly cleaned and neatly styled. Long hair should be worn as conservatively as possible. </li></ul></body></html>";
			title.setText("Professional Dress");
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.custom_title_bar);
			webview.loadData(rowElement, "text/html", null);
			break;
		default:
			break;
		}
	}

	private void getResumeView(int rowIndex) {
		WebView webview;
		String rowElement = "";
		webview = new WebView(this);
		setContentView(webview);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		TextView title= (TextView) findViewById(R.id.myTitle);
		webview.setBackgroundResource(R.drawable.white);
		webview.setBackgroundColor(0);
		switch (rowIndex) {
		case 0:
			title.setText("Contact Info");
			String locHrs = "<html><body><ul><li>First and last name, address, phone number, and professional email address displayed clearly at the top of the page.  </li><br/><li>Name is 14&#45;16 pt font size. </li></ul></body></html>";
			webview.loadData(locHrs, "text/html", null);
			break;
		case 1:
			title.setText("Objective");
			String summary2 = "<html><body><ul><li>Objective statement only included if it enhances the resume by making career goal clearer to the recipient. Objective statement is brief and specific, stating interest in the field and/or position. </li><br/><li>No first person pronouns are used (I, me, my). </li><br/><li>In lieu of an objective statement, you may include a Summary section to list your job related skills. </li></ul></body></html>";
			webview.loadData(summary2, "text/html", null);
			break;
		case 2:
			String secRow = "<html><body><ul><li>Schools attended are listed in reverse chronological order. </li><br/><li>Full name of the university (University of Missouri&#45;Kansas City) spelled out and the city and state of its location listed. </li><br/><li>Use the official degree name (Bachelor of Arts in English Literature, Bachelor of Science in Computer Science). </li><br/><li>Major, minor, concentration, and emphasis (if applicable) spelled out. </li><br/><li>Include GPA if it is above a 3.00 listed to the hundredths place. </li><br/><li>Indicate graduation date, anticipated graduation date, or dates attended (if not a degree granting program, i.e. Study Abroad). </li><br/><li>If you have finished your First&#45;Year of college, do not include your high school information </li></ul></body></html>";
			title.setText("Education");
			webview.loadData(secRow, "text/html", null);
			break;
		case 3:
			String thirdRow = "<html><body><ul><li>Present experience in reverse chronological order (most recent job/position listed first). </li><br/><li>Indicates the name, city, and state of the organization or company. Distinguishing markers such as bold, italics, and underline are consistent and minimal. </li><br/><li>List title and start/end dates (month/year or semester/year &#45; used consistently throughout the resume). </li><br/><li>Use descriptive, bulleted statements demonstrating skills, accomplishments, and specific responsibilities. Each statement starts with an action verb, not an &#34;I&#34; statement. Full sentences are not used on the resume. </li><br/><li>Use past tense verbs for past experiences and present tense verbs for current experiences. </li></ul></body></html>";
			title.setText("Experience");
			webview.loadData(thirdRow, "text/html", null);
			break;
		case 4:
			rowElement = "<html><body><ul><li>Use a standard font (no color) and size for the body that is easy to read (10&#45;12pt). </li><br/><li>NO TYPOS on the resume. </li><br/><li>For undergraduate students, it is recommended that resumes do not exceed one page in length. </li><br/><li>Style and layout are consistent throughout the resume including consistent use of bullets, bold, italics, underline, hyphens, punctuation, and indentation; Uses standard resume format (no design). </li></ul></body></html>";
			title.setText("Layout & Design");
			webview.loadData(rowElement, "text/html", null);
			break;
		case 5:
			rowElement = "<html><body><ul><li>Specify the complete name for each relevant award or honor, the granting organization, and the month/year of receipt. </li></ul></body></html>";
			title.setText("Honors & Awards");
			webview.loadData(rowElement, "text/html", null);
			break;
		case 6:
			rowElement = "<html><body><ul><li>List the correct name of each organization (do not use acronyms), leadership roles if applicable, and dates of involvement. May also include a brief description of tasks/accomplishments using action verbs. </li></ul></body></html>";
			title.setText("Activities");
			webview.loadData(rowElement, "text/html", null);
			break;
		case 7:
			rowElement = "<html><body><ul><li>Include a brief list of computer skills such as Word, Excel, PowerPoint, QuickBooks, etc. Only list if proficient. </li><br/><li>List any languages spoken (other than English) and level of proficiency. </li><br/><li>If applicable, list laboratory skills or additional field&#45;specific skills. </li><br/><li>If applicable, list proficiency in social media, blogging and/or Klout Score </li></ul></body></html>";
			title.setText("Skills");
			webview.loadData(rowElement, "text/html", null);
			break;
		case 8:
			rowElement = "<html><body><ul><li>Does not include any of the following personal items: photograph, marital status, date of birth, social security number, citizenship status, gender, ethnicity, religion or political affiliation. </li><br/><li>References not included within the resume. Resume should NOT say &#34;References available upon request.&#34; </li></ul></body></html>";
			title.setText("Additional Info");
			webview.loadData(rowElement, "text/html", null);
			break;

		default:
			break;
		}
	}

	private void getCoverLetterView(int rowIndex) {
		WebView webview;
		switch (rowIndex) {
		case 0:
			webview = new WebView(this);
			setContentView(webview);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.custom_title_bar);
			TextView title= (TextView) findViewById(R.id.myTitle);
			title.setText("Cover Letter Tips");
			String locHrs = "<html><body><h3><font color=\"#0F69B9\"> Tips </font></h3><ul><li>Introduce you and your resume to a potential employer.</li><br/><li>Capture the reader&#39;s attention.</li><br/><li>Explain your purpose in sending your resume.</li><br/><li>Be a persuasive marketing tool that highlights aspects of your experience/education.</li><br/><li>Convey your qualifications for the position, your professional communication style, as well as your interest and enthusiasm in working for the organization.</li><br/><li>Focus on the employer needs (do not send a form cover letter).</li><br/><li>Stimulate the employer&#39;s action (to interview you for the position).</li><br/><li>Be sent with your resume each time you contact a potential employer.</li><br/><li>Include a formal salutation.</li></ul></body></html>";
			webview.loadData(locHrs, "text/html", null);
			break;
		case 1:
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(CoverLetterProvider.CONTENT_URI + "cover_letter_samples.pdf"));
			// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			try {
				startActivity(intent);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(ResumeDevListContents.this,
						"No Application Available to View PDF",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

	
}
