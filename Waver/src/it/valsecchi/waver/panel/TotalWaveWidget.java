package it.valsecchi.waver.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;


public class TotalWaveWidget extends JPanel{

	private static final long serialVersionUID = -8001414059016730700L;
	public JButton btnStart;
	public JButton btnStop;
	public JTextField txtTime;
	public JButton btnSet;
	public JLabel lblTime;
	public JButton btnInterferenza;

	public TotalWaveWidget() {
		
		lblTime = new JLabel("t=");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtTime = new JTextField();
		txtTime.setColumns(10);
		
		btnSet = new JButton("SET");
		
		btnStart = new JButton("START");
		
		btnStop = new JButton("STOP");
		
		btnInterferenza = new JButton("INTERFERENZA");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInterferenza)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnStart)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnStop))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTime)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtTime, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnSet)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTime)
						.addComponent(btnSet)
						.addComponent(txtTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart)
						.addComponent(btnStop))
					.addGap(18)
					.addComponent(btnInterferenza)
					.addContainerGap(213, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
