package it.valsecchi.waver.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;


public class LocalWaveWidget extends JPanel{

	private static final long serialVersionUID = -8001414059016730700L;
	public JTextField txtPosition;
	public JButton btnSetX;
	public JLabel lblPosition;
	public JButton btnInterferenza;
	public JTextField txtMaxX;
	public JLabel lblMaxY;
	public JTextField txtMaxY;
	public JButton btnMaxY;
	public JButton btnMaxX;
	public JLabel lblLabelInterval;
	public JTextField txtInterval;
	public JButton btnInterval;

	public LocalWaveWidget() {
		
		lblPosition = new JLabel("x=");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtPosition = new JTextField();
		txtPosition.setColumns(10);
		
		btnSetX = new JButton("SET");
		
		btnInterferenza = new JButton("INTERFERENZA");
		
		JLabel lblMaxX = new JLabel("max X=");
		
		txtMaxX = new JTextField();
		txtMaxX.setColumns(10);
		
		btnMaxX = new JButton("SET");
		
		lblMaxY = new JLabel("max Y=");
		
		txtMaxY = new JTextField();
		txtMaxY.setColumns(10);
		
		btnMaxY = new JButton("SET");
		
		lblLabelInterval = new JLabel("label interval:");
		
		txtInterval = new JTextField();
		txtInterval.setColumns(10);
		
		btnInterval = new JButton("SET");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblMaxY, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtMaxY, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnMaxY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblMaxX)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtMaxX, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnMaxX)))
						.addComponent(btnInterferenza)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPosition)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtPosition, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnSetX))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLabelInterval, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtInterval, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnInterval)))
					.addContainerGap(222, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosition)
						.addComponent(btnSetX)
						.addComponent(txtPosition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(71)
					.addComponent(btnInterferenza)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxX)
						.addComponent(txtMaxX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMaxX))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxY)
						.addComponent(txtMaxY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMaxY))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLabelInterval)
						.addComponent(txtInterval, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInterval))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
