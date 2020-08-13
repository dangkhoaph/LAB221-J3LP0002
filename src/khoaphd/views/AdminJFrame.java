package khoaphd.views;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import khoaphd.dtos.BookDTO;
import khoaphd.models.BookDAO;

/**
 *
 * @author KhoaPHD
 */
public class AdminJFrame extends javax.swing.JFrame {

    /**
     * Creates new form AdminJFrame
     */
    private String adminID;
    private BookDAO booksDAO;
    private DefaultTableModel modelBooks;
    private final static boolean IS_UPDATING = true;

    public AdminJFrame() {
        initComponents();
    }

    public AdminJFrame(String id) {
        initComponents();
        adminID = id;
        setLocationRelativeTo(null);
        setTitle("Admin - ID " + adminID);
        lbWelcome.setText("Welcome Admin " + adminID);
        booksDAO = new BookDAO();
        modelBooks = (DefaultTableModel)tblBooks.getModel();
        try {
            setModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupNewMode();
    }

    private void setupNewMode() {
        btnInsert.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        txtBookID.setEditable(true);
        txtImportDate.setEditable(false);
        txtImportDate.setText("<Autofill>");
    }

    private void setupEditMode() {
        btnInsert.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        txtBookID.setEditable(false);
        txtImportDate.setEditable(true);
    }

    private void setModel() throws Exception {
        modelBooks.setRowCount(0);
        Vector<BookDTO> list = booksDAO.getAllBooksForAdmin();
        for (BookDTO dto : list) {
            modelBooks.addRow(dto.toVectorLong());
        }
    }

    private void setModelToSearch(Vector<BookDTO> list) throws Exception {
        modelBooks.setRowCount(0);
        for (BookDTO dto : list) {
            modelBooks.addRow(dto.toVectorLong());
        }
    }

    private boolean validatePrice(String priceStr) {
        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please input a valid float number for price");
            return false;
        }
        if (price <= 0) {
            JOptionPane.showMessageDialog(this, "Price must be positive");
            return false;
        }
        return true;
    }

    private boolean validateAll(boolean isUpdate) {
        if (!isUpdate) {
            String id = txtBookID.getText().trim().toUpperCase();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID is empty");
                txtBookID.requestFocus();
                return false;
            }
            if (!id.matches("^B\\d{6}$")) {
                JOptionPane.showMessageDialog(this, "ID must follow the format like B000000");
                txtBookID.requestFocus();
                return false;
            }
        }

        String title = txtTitle.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title is empty");
            txtTitle.requestFocus();
            return false;
        }

        String author = txtAuthor.getText().trim();
        if (author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Author is empty");
            txtAuthor.requestFocus();
            return false;
        }

        String category = txtCategory.getText().trim();
        if (category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Category is empty");
            txtCategory.requestFocus();
            return false;
        }

        String priceStr = txtPrice.getText().trim();
        if (!validatePrice(priceStr)) {
            txtPrice.requestFocus();
            return false;
        }

        String quantityStr = txtQuantity.getText().trim();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please input a valid integer for quantity");
            txtQuantity.requestFocus();
            return false;
        }
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be positive");
            txtQuantity.requestFocus();
            return false;
        }
        
        if (isUpdate) {
            String importDateStr = txtImportDate.getText().trim();
            if (!importDateStr.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                JOptionPane.showMessageDialog(this, "Date must follow the format YYYY-MM-DD");
                txtImportDate.requestFocus();
                return false;
            }
            Date currentDate = new Date(System.currentTimeMillis());
            Date importDate = null;
            try {
                importDate = convertStringToDate(importDateStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date");
                txtImportDate.requestFocus();
                return false;
            }
            if (importDate.compareTo(currentDate) > 0) {
                JOptionPane.showMessageDialog(this, "Import date cannot be after current date");
                txtImportDate.requestFocus();
                return false;
            }
        }

        return true;
    }
    
    private String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(date);
        return s;
    }
    
    private Date convertStringToDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        java.util.Date date = dateFormat.parse(dateStr);
        Date sqlDate = new Date(date.getTime());
        return sqlDate;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbWelcome = new javax.swing.JLabel();
        btnViewCodes = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTo = new javax.swing.JTextField();
        btnSearchByPrice = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooks = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtBookID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCategory = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtImageName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtImportDate = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearchByTitle = new javax.swing.JButton();
        btnSearchByCategory = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Administration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.blue)); // NOI18N

        lbWelcome.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbWelcome.setForeground(java.awt.Color.blue);
        lbWelcome.setText(" ");

        btnViewCodes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnViewCodes.setText("View discount codes");
        btnViewCodes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCodesActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbWelcome)
                .addGap(30, 30, 30)
                .addComponent(btnViewCodes)
                .addGap(30, 30, 30)
                .addComponent(btnLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnViewCodes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogout))
                        .addGap(2, 2, 2))
                    .addComponent(lbWelcome, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLogout, btnViewCodes});

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("From:");

        txtFrom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFrom.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("To:");

        txtTo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTo.setPreferredSize(new java.awt.Dimension(6, 30));

        btnSearchByPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearchByPrice.setText("Search by Price Range");
        btnSearchByPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByPriceActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReset.setText("Reset Table");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tblBooks.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Title", "Author", "Category", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBooks.setRowHeight(20);
        tblBooks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBooksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBooks);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.blue)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Book ID:");

        txtBookID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBookID.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Title:");

        txtTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Author:");

        txtAuthor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Category:");

        txtCategory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCategory.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Description:");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDescription);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Image name:");

        txtImageName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtImageName.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Price:");

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPrice.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Quantity:");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQuantity.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Import Date:");

        txtImportDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtImportDate.setPreferredSize(new java.awt.Dimension(6, 30));

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnInsert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearchByTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearchByTitle.setText("Search by Title");
        btnSearchByTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByTitleActionPerformed(evt);
            }
        });

        btnSearchByCategory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearchByCategory.setText("Search by Category");
        btnSearchByCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtTitle)
                            .addComponent(txtCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBookID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImageName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImportDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnSearchByTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnSearchByCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAuthor, txtBookID, txtCategory, txtTitle});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtImageName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtImportDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchByTitle)
                    .addComponent(btnSearchByCategory)
                    .addComponent(btnNew)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(30, 30, 30))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtAuthor, txtBookID, txtImageName, txtTitle});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchByPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchByPrice)
                    .addComponent(btnReset)
                    .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBooksMouseClicked
        int row = tblBooks.getSelectedRow();
        String id = (String)tblBooks.getValueAt(row, 0);
        BookDTO dto = null;
        try {
            dto = booksDAO.getBookByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtBookID.setText(dto.getBookID());
        txtTitle.setText(dto.getTitle());
        txtAuthor.setText(dto.getAuthor());
        txtCategory.setText(dto.getCategory());
        txtDescription.setText(dto.getDescription());
        txtImageName.setText(dto.getImageName());
        txtPrice.setText(Double.toString(dto.getPrice()));
        txtQuantity.setText(Integer.toString(dto.getQuantity()));
        txtImportDate.setText(convertDateToString(dto.getImportDate()));
        setupEditMode();
    }//GEN-LAST:event_tblBooksMouseClicked

    private void btnSearchByTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByTitleActionPerformed
        String title = txtTitle.getText().trim();
        Vector<BookDTO> list = null;
        try {
            list = booksDAO.getBooksByLikeTitle(title);
            setModelToSearch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchByTitleActionPerformed

    private void btnSearchByCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByCategoryActionPerformed
        String category = txtCategory.getText().trim();
        Vector<BookDTO> list = null;
        try {
            list = booksDAO.getBooksByLikeCategory(category);
            setModelToSearch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchByCategoryActionPerformed

    private void btnSearchByPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByPriceActionPerformed
        String fromStr = txtFrom.getText().trim();
        if (!validatePrice(fromStr)) {
            txtFrom.requestFocus();
            return;
        }

        String toStr = txtTo.getText().trim();
        if (!validatePrice(toStr)) {
            txtTo.requestFocus();
            return;
        }
        
        double from = 0.0;
        try {
            from = Double.parseDouble(fromStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid float number");
            txtFrom.requestFocus();
            return;
        }
        
        double to = 0.0;
        try {
            to = Double.parseDouble(toStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid float number");
            txtTo.requestFocus();
            return;
        }
        if (from > to) {
            double tmp = from;
            from = to;
            to = tmp;
        }

        Vector<BookDTO> list = null;
        try {
            list = booksDAO.getBooksByPriceRange(from, to);
            setModelToSearch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchByPriceActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        try {
            setModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtBookID.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
        txtDescription.setText("");
        txtImageName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtBookID.requestFocus();
        setupNewMode();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        if (!validateAll(!IS_UPDATING)) {
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to insert this book?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }
        
        String id = txtBookID.getText().trim().toUpperCase();
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String category = txtCategory.getText().trim();
        String description = txtDescription.getText().trim();
        String imageName = txtImageName.getText().trim();
        double price = Double.parseDouble(txtPrice.getText().trim());
        int quantity = Integer.parseInt(txtQuantity.getText().trim());
        Date importDate = new Date(System.currentTimeMillis());
        BookDTO dto = new BookDTO(id, title, author, category, description,
                imageName, price, quantity, importDate);
        boolean result = false;
        try {
            result = booksDAO.insert(dto);
            if (result) {
                JOptionPane.showMessageDialog(this, "Insert book successfully");
                setModel();
            } else {
                JOptionPane.showMessageDialog(this, "Insert book failed");
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                JOptionPane.showMessageDialog(this, "ID already existed");
            } else {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!validateAll(IS_UPDATING)) {
            return;
        }

        String id = txtBookID.getText().trim().toUpperCase();
        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to update book ID " + id + "?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String category = txtCategory.getText().trim();
        String description = txtDescription.getText().trim();
        String imageName = txtImageName.getText().trim();
        double price = Double.parseDouble(txtPrice.getText().trim());
        int quantity = Integer.parseInt(txtQuantity.getText().trim());
        Date importDate = null;
        try {
            importDate = convertStringToDate(txtImportDate.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        BookDTO dto = new BookDTO(id, title, author, category, description,
                imageName, price, quantity, importDate);
        boolean result = false;
        try {
            result = booksDAO.update(dto);
            if (result) {
                JOptionPane.showMessageDialog(this, "Update book successfully");
                setModel();
            } else {
                JOptionPane.showMessageDialog(this, "Update book failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = txtBookID.getText().trim().toUpperCase();
        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to delete book ID " + id + "?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }
        
        boolean result = false;
        try {
            result = booksDAO.delete(id);
            if (result) {
                JOptionPane.showMessageDialog(this, "Delete book successfully");
                setModel();
                setupNewMode();
            } else {
                JOptionPane.showMessageDialog(this, "Update book failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to logout?");
        if (confirmation == JOptionPane.YES_OPTION) {
            new LoginJFrame().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewCodesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCodesActionPerformed
        new DiscountJFrame().setVisible(true);
    }//GEN-LAST:event_btnViewCodesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearchByCategory;
    private javax.swing.JButton btnSearchByPrice;
    private javax.swing.JButton btnSearchByTitle;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnViewCodes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbWelcome;
    private javax.swing.JTable tblBooks;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtBookID;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtImageName;
    private javax.swing.JTextField txtImportDate;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtTo;
    // End of variables declaration//GEN-END:variables
}
