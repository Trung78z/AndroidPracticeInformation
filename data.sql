-- Sinh viên 1
INSERT INTO user_info VALUES (
    0,
    'Lê Thị Minh Anh',
    'Minh Anh',
    'minhanh.le@example.com',
    '20200456',
    'Nữ',
    '15-03-2002',
    'Công nghệ thông tin',
    '+84 987654321',
    'Hà Nội, Việt Nam',
    'Đang học'
);

-- Sinh viên 2
INSERT INTO user_info VALUES (
    0,
    'Trần Văn Bảo',
    'Văn Bảo',
    'vanbao.tran@example.com',
    '20200567',
    'Nam',
    '22-11-2001',
    'Kỹ thuật điện',
    '+84 912345678',
    'Đà Nẵng, Việt Nam',
    'Đang học'
);

-- Sinh viên 3
INSERT INTO user_info VALUES (
    0,
    'Phạm Thị Cẩm Tú',
    'Cẩm Tú',
    'camtu.pham@example.com',
    '20200678',
    'Nữ',
    '08-09-2002',
    'Quản trị kinh doanh',
    '+84 933333333',
    'Cần Thơ, Việt Nam',
    'Đang học'
);

-- Sinh viên 4
INSERT INTO user_info VALUES (
    0,
    'Ngô Đức Dũng',
    'Đức Dũng',
    'ducdung.ngo@example.com',
    '20200789',
    'Nam',
    '30-01-2001',
    'Kỹ thuật cơ khí',
    '+84 944444444',
    'Hải Phòng, Việt Nam',
    'Đã tốt nghiệp'
);

-- Sinh viên 5
INSERT INTO user_info VALUES (
    0,
    'Vũ Thị Eo Éo',
    'Eo Éo',
    'eo.eo@example.com',
    '20200890',
    'Nữ',
    '12-12-2000',
    'Ngôn ngữ Anh',
    '+84 955555555',
    'Nha Trang, Việt Nam',
    'Bảo lưu'
);

INSERT INTO user_info (
    image,
    fullName,
    shortName,
    email,
    studentId,
    gender,
    dob,
    major,
    phone,
    address,
    status
) VALUES (
    2131230857,                          -- No image specified
    'Nguyễn Thành Trung',      -- fullName
    'Trung',                   -- shortName
    'trungpspy@gmail.com',     -- email
    '20200381',                -- studentId
    'MALE',                     -- gender
    '20-06-2002',              -- dob
    'Điện tử viễn thông',      -- major
    '+84 886506127',           -- phone
    'TPHCM, Việt Nam',         -- address
    'Đang học'                 -- status
);