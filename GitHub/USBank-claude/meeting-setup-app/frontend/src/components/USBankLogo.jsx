export default function USBankLogo() {
  return (
    <svg width="180" height="44" viewBox="0 0 180 44" xmlns="http://www.w3.org/2000/svg" aria-label="U.S. Bank">
      {/* Red shield icon */}
      <rect x="0" y="4" width="38" height="36" rx="4" fill="#CC0000" />
      <text x="7" y="27" fontFamily="Arial, sans-serif" fontWeight="bold" fontSize="15" fill="#fff">US</text>
      {/* Gold divider */}
      <rect x="43" y="4" width="3" height="36" fill="#B9975B" />
      {/* Navy wordmark */}
      <text x="52" y="30" fontFamily="Arial, sans-serif" fontWeight="bold" fontSize="20" fill="#fff">U.S. Bank</text>
    </svg>
  )
}
