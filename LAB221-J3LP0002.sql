USE [master]
GO
/****** Object:  Database [BookStore]    Script Date: 8/13/2020 11:47:29 PM ******/
CREATE DATABASE [BookStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BookStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\BookStore.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'BookStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\BookStore_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [BookStore] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BookStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookStore] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BookStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookStore] SET RECOVERY FULL 
GO
ALTER DATABASE [BookStore] SET  MULTI_USER 
GO
ALTER DATABASE [BookStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookStore] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [BookStore] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'BookStore', N'ON'
GO
USE [BookStore]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 8/13/2020 11:47:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[UserID] [varchar](5) NOT NULL,
	[Password] [varchar](50) NULL,
	[Fullname] [varchar](50) NULL,
	[Role] [varchar](10) NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 8/13/2020 11:47:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Bill](
	[BillID] [int] NOT NULL,
	[UserID] [varchar](5) NOT NULL,
	[Total] [float] NULL,
	[BuyDate] [date] NULL,
	[DiscountID] [varchar](5) NULL,
	[TotalAfterDiscount] [float] NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[BillID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Book]    Script Date: 8/13/2020 11:47:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Book](
	[BookID] [varchar](7) NOT NULL,
	[Title] [varchar](50) NULL,
	[Author] [varchar](50) NULL,
	[Category] [varchar](50) NULL,
	[Description] [nvarchar](500) NULL,
	[ImageName] [varchar](50) NULL,
	[Price] [float] NULL,
	[Quantity] [int] NULL,
	[ImportDate] [date] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Books] PRIMARY KEY CLUSTERED 
(
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BookInBill]    Script Date: 8/13/2020 11:47:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BookInBill](
	[BillID] [int] NOT NULL,
	[BookID] [varchar](7) NOT NULL,
	[Price] [float] NULL,
	[Quantity] [int] NULL,
	[Total] [float] NULL,
 CONSTRAINT [PK_BookInBill] PRIMARY KEY CLUSTERED 
(
	[BillID] ASC,
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 8/13/2020 11:47:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Discount](
	[DiscountID] [varchar](5) NOT NULL,
	[UserID] [varchar](5) NOT NULL,
	[DiscountPercent] [float] NULL,
	[ExpiredDate] [date] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[DiscountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Account] ([UserID], [Password], [Fullname], [Role]) VALUES (N'U0001', N'123456', N'Dang Khoa', N'Admin')
INSERT [dbo].[Account] ([UserID], [Password], [Fullname], [Role]) VALUES (N'U0002', N'123456', N'The Hien', N'Admin')
INSERT [dbo].[Account] ([UserID], [Password], [Fullname], [Role]) VALUES (N'U0003', N'123456', N'Quynh Nhu', N'User')
INSERT [dbo].[Account] ([UserID], [Password], [Fullname], [Role]) VALUES (N'U0004', N'123456', N'Trung Phong', N'User')
INSERT [dbo].[Account] ([UserID], [Password], [Fullname], [Role]) VALUES (N'U0005', N'123456', N'Quynh Trang', N'User')
INSERT [dbo].[Account] ([UserID], [Password], [Fullname], [Role]) VALUES (N'U0006', N'123456', N'Nhut Quang', N'User')
INSERT [dbo].[Bill] ([BillID], [UserID], [Total], [BuyDate], [DiscountID], [TotalAfterDiscount]) VALUES (1, N'U0005', 128000, CAST(N'2020-03-19' AS Date), N'D0099', 12800)
INSERT [dbo].[Bill] ([BillID], [UserID], [Total], [BuyDate], [DiscountID], [TotalAfterDiscount]) VALUES (2, N'U0005', 1020000, CAST(N'2020-03-19' AS Date), NULL, 1020000)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000001', N'Nha Gia Kim', N'Paulo Coelho', N'Fiction, Adventure, Mystery', N'Tiểu thuyết Nhà giả kim của Paulo Coelho như một câu chuyện cổ tích giản dị, nhân ái, giàu chất thơ, thấm đẫm những minh triết huyền bí của phương Đông.', N'nhagiakim', 69000, 5, CAST(N'2020-01-03' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000002', N'Dac Nhan Tam', N'Dale Carnegie', N'Self-help', N'Đắc nhân tâm của Dale Carnegie là quyển sách duy nhất về thể loại self-help liên tục đứng đầu danh mục sách bán chạy nhất (best-selling Books) do báo The New York Times bình chọn suốt 10 năm liền.', N'dacnhantam', 76000, 5, CAST(N'2020-01-07' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000003', N'Tuoi Tre Dang Gia Bao Nhieu', N'Rosie Nguyen', N'Self-help', N'Hãy đọc sách, nếu bạn đọc sách một cách bền bỉ, sẽ đến lúc bạn bị thôi thúc không ngừng bởi ý muốn viết nên cuốn sách của riêng mình.', N'tuoitredanggiabaonhieu', 70000, 5, CAST(N'2020-01-07' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000004', N'5 Centimet Tren Giay', N'Shinkai Makoto', N'Fiction, Romance, Light Novel', N'Bằng giọng văn tinh tế, truyền cảm, 5 centimet trên giây mang đến những khắc họa mới về tâm hồn và khả năng tồn tại của cảm xúc, bắt đầu từ tình yêu trong sáng, ngọt ngào của một cô bé và cậu bé.', N'5cmtrengiay', 50000, 11, CAST(N'2020-01-10' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000005', N'Toi Tai Gioi, Ban Cung The', N'Adam Khoo', N'Self-help, Education', N'Tôi tài giỏi, bạn cũng thế ! tổng hợp những kỹ năng và phương pháp đã mang tới thành công vượt bậc cho cậu bé Adam kém cõi và dĩ nhiên bạn cũng có thể thành công như vậy!', N'toitaigioibancungthe', 118000, 12, CAST(N'2020-01-13' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000006', N'Chuyen Con Meo Day Hai Au Bay', N'Luis Sepulveda', N'Fiction', N'Cuốn sách mở đầu cho mùa hè với minh họa dễ thương, hài hước là món quà dành cho mọi trẻ em và người lớn.', N'chuyenconmeodayhaiaubay', 49000, 5, CAST(N'2020-01-18' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000007', N'Cam Nang Tu Hoc Ielts', N'Kien Tran', N'Education', N'Cuốn sách này khác với các cuốn sách khác ở chỗ nó tập trung thay đổi belief system của bạn thay vì cố nhồi nhét thêm từ vựng hay ngữ pháp khiến bạn… gấp sách lại để năm sau đọc tiếp.', N'camnangtuhocielts', 100000, 5, CAST(N'2020-01-18' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000008', N'Co Hai Con Meo Ngoi Ben Cua So', N'Nguyen Nhat Anh', N'Light Novel', N'Cuốn truyện có độ dầy vừa phải, 67 hình vẽ của họa sĩ Đỗ Hoàng Tường sinh động đến từng nét nũng nịu hay kiêu căng của nàng mèo người yêu mèo Gấu, câu chuyện thì hấp dẫn duyên dáng điểm những bài thơ tình lãng mạn nao lòng song đọc to lên thì khiến cười hinh hích…', N'cohaiconmeongoibencuaso', 85000, 0, CAST(N'2020-01-25' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000009', N'De Men Phieu Luu Ky', N'To Hoai', N'Fiction, Adventure', N'Cuốn sách là món quà đẹp cả về hình thức lẫn nội dung, là món quà ý nghĩa và bổ ích mà các bậc phụ huynh tặng cho con em của mình.', N'demenphieuluuky', 150000, 12, CAST(N'2020-01-31' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000010', N'Su Im Lang Cua Bay Cuu', N'Thomas Harris', N'Fiction, Horror, Mystery', N'Sự im lặng của bầy cừu hội tụ đầy đủ những yếu tố làm nên một cuốn tiểu thuyết trinh thám kinh dị xuất sắc nhất.', N'suimlangcuabaycuu', 115000, 9, CAST(N'2020-02-01' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000011', N'Code Dao Ky Su', N'Pham Huy Hoang', N'Technology', N'Nếu các bạn có đọc các blog về lập trình ở Việt Nam thì có lẽ cái tên Tôi đi code dạo không có gì quá xa lạ đối với các bạn.', N'codedaokysu', 159000, 11, CAST(N'2020-02-07' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000012', N'Tren Duong Bang', N'Tony Buoi Sang', N'Self-help', N'Những bài viết của Tony sinh động, thiết thực, hài hước và xuất phát từ cái tâm trong sáng của một người đi trước nhiều kinh nghiệm.', N'trenduongbang', 43500, 11, CAST(N'2020-02-18' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000013', N'Dekiru Nihongo Beginner', N'Unknown', N'Education', N'Giáo trình tiếng Nhật', N'dekirunihongo', 100000, 5, CAST(N'2020-02-20' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000014', N'Networking', N'Unknow', N'Education', N'Sách giáo trình môn Network', N'networking', 73000, 17, CAST(N'2020-03-01' AS Date), 1)
INSERT [dbo].[Book] ([BookID], [Title], [Author], [Category], [Description], [ImageName], [Price], [Quantity], [ImportDate], [Status]) VALUES (N'B000015', N'testbook', N'test', N'Fiction', N'test', N'test.png', 1000, 5, CAST(N'2020-03-19' AS Date), 1)
INSERT [dbo].[BookInBill] ([BillID], [BookID], [Price], [Quantity], [Total]) VALUES (1, N'B000005', 118000, 1, 118000)
INSERT [dbo].[BookInBill] ([BillID], [BookID], [Price], [Quantity], [Total]) VALUES (1, N'B000015', 1000, 10, 10000)
INSERT [dbo].[BookInBill] ([BillID], [BookID], [Price], [Quantity], [Total]) VALUES (2, N'B000008', 85000, 12, 1020000)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0001', N'U0003', 75, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0002', N'U0005', 25, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0003', N'U0004', 50, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0004', N'U0003', 95, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0005', N'U0006', 10, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0006', N'U0003', 50, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0007', N'U0005', 25, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0008', N'U0003', 25, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0009', N'U0004', 35, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0010', N'U0006', 35, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0011', N'U0005', 10, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0012', N'U0006', 75, CAST(N'2020-03-30' AS Date), 0)
INSERT [dbo].[Discount] ([DiscountID], [UserID], [DiscountPercent], [ExpiredDate], [Status]) VALUES (N'D0099', N'U0005', 90, CAST(N'2020-03-30' AS Date), 0)
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Account] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK_Bill_Account]
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Discount] FOREIGN KEY([DiscountID])
REFERENCES [dbo].[Discount] ([DiscountID])
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK_Bill_Discount]
GO
ALTER TABLE [dbo].[BookInBill]  WITH CHECK ADD  CONSTRAINT [FK_BookInBill_Bill] FOREIGN KEY([BillID])
REFERENCES [dbo].[Bill] ([BillID])
GO
ALTER TABLE [dbo].[BookInBill] CHECK CONSTRAINT [FK_BookInBill_Bill]
GO
ALTER TABLE [dbo].[BookInBill]  WITH CHECK ADD  CONSTRAINT [FK_BookInBill_Book] FOREIGN KEY([BookID])
REFERENCES [dbo].[Book] ([BookID])
GO
ALTER TABLE [dbo].[BookInBill] CHECK CONSTRAINT [FK_BookInBill_Book]
GO
ALTER TABLE [dbo].[Discount]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Account] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[Discount] CHECK CONSTRAINT [FK_Discount_Account]
GO
USE [master]
GO
ALTER DATABASE [BookStore] SET  READ_WRITE 
GO
